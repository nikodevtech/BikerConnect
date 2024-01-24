package com.bikerconnect.servicios;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.UsuarioDTO;
import com.bikerconnect.entidades.Usuario;
import com.bikerconnect.repositorios.UsuarioRepositorio;

import jakarta.transaction.Transactional;

/**
 * Servicio que implementa los metodos de la interface {@link IUsuarioServicio}
 * y en esta clase es donde se entra al detalle de la logica de dichos métodos
 * para la gestión de usuarios.
 */
@Service
@Transactional
public class UsuarioServicioImpl implements IUsuarioServicio {

	@Autowired
	private UsuarioRepositorio repositorio;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioToDao toDao;

	@Autowired
	private IEmailServicio emailServicio;

	@Autowired
	private IUsuarioToDto toDto;

	@Override
	public UsuarioDTO registrarUsuario(UsuarioDTO userDto) {

		try {
			// Comprueba si ya existe un usuario con el email que quiere registrar
			Usuario usuarioDaoByEmail = repositorio.findFirstByEmail(userDto.getEmailUsuario());

			if (usuarioDaoByEmail != null) { // El email se encuentra registrado
				return null;
			}

			// Si continua la ejecución es que el email no se encuentra ya registrado
			userDto.setClaveUsuario(passwordEncoder.encode(userDto.getClaveUsuario()));
			Usuario usuarioDao = toDao.usuarioToDao(userDto);
			usuarioDao.setFechaRegistro(Calendar.getInstance());
			usuarioDao.setRol("ROLE_USER");
			if (userDto.isCuentaConfirmada()) {
				usuarioDao.setCuentaConfirmada(true);
				repositorio.save(usuarioDao);
			} else {
				usuarioDao.setCuentaConfirmada(false);
				// Generar token de confirmación
				String token = passwordEncoder.encode(RandomStringUtils.random(30));
				usuarioDao.setToken(token);

				// Guardar el usuario en la base de datos
				repositorio.save(usuarioDao);

				// Enviar el correo de confirmación
				String nombreUsuario = usuarioDao.getNombreApellidos();
				emailServicio.enviarEmailConfirmacion(userDto.getEmailUsuario(), nombreUsuario, token);
			}

			return userDto;
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error UsuarioServicioImpl - registrarUsuario() ]" + iae.getMessage());
		} catch (Exception e) {
			System.out.println("[Error UsuarioServicioImpl - registrarUsuario() ]" + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean iniciarProcesoRecuperacion(String emailUsuario) {
		try {
			Usuario usuarioExistente = repositorio.findFirstByEmail(emailUsuario);

			if (usuarioExistente != null) {
				// Generar el token y establece la fecha de expiración
				String token = passwordEncoder.encode(RandomStringUtils.random(30));
				Calendar fechaExpiracion = Calendar.getInstance();
				fechaExpiracion.add(Calendar.MINUTE, 10);
				// Actualizar el usuario con el nuevo token y la fecha de expiración
				usuarioExistente.setToken(token);
				usuarioExistente.setExpiracionToken(fechaExpiracion);

				// Actualizar el usuario en la base de datos
				repositorio.save(usuarioExistente);

				// Enviar el correo de recuperación
				String nombreUsuario = usuarioExistente.getNombreApellidos();
				emailServicio.enviarEmailRecuperacion(emailUsuario, nombreUsuario, token);

				return true;

			} else {
				System.out.println("[Error UsuarioServicioImpl - iniciarProcesoRecuperacion()] El usuario con email "
						+ emailUsuario + " no existe");
				return false;
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error UsuarioServicioImpl - iniciarProcesoRecuperacion() ]" + iae.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("[Error UsuarioServicioImpl - iniciarProcesoRecuperacion()]" + e.getMessage());
			return false;
		}
	}

	@Override
	public UsuarioDTO obtenerUsuarioPorToken(String token) {
		Usuario usuarioExistente = repositorio.findByToken(token);

		if (usuarioExistente != null) {
			UsuarioDTO usuario = toDto.usuarioToDto(usuarioExistente);
			return usuario;
		} else {
			System.out.println("No existe el usuario con el token " + token);
			return null;
		}

	}

	@Override
	public boolean modificarContraseñaConToken(UsuarioDTO usuario) {

		Usuario usuarioExistente = repositorio.findByToken(usuario.getToken());

		if (usuarioExistente != null) {
			String nuevaContraseña = passwordEncoder.encode(usuario.getPassword());
			usuarioExistente.setPassword(nuevaContraseña);
			usuarioExistente.setToken(null); // Se setea a null para invalidar el token ya consumido al cambiar de
												// password
			repositorio.save(usuarioExistente);

			return true;
		}

		return false;
	}

	@Override
	public boolean confirmarCuenta(String token) {
		try {
			Usuario usuarioExistente = repositorio.findByToken(token);

			if (usuarioExistente != null && !usuarioExistente.isCuentaConfirmada()) {
				// Entra en esta condición si el usuario existe y su cuenta no se ha confirmado
				usuarioExistente.setCuentaConfirmada(true);
				usuarioExistente.setToken(null);
				repositorio.save(usuarioExistente);

				return true;
			} else {
				System.out.println(
						"[Error UsuarioServicioImpl - confirmarCuenta()] La cuenta no existe o ya está confirmada");
				return false;
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error UsuarioServicioImpl - confirmarCuenta()] " + iae.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("[Error UsuarioServicioImpl - confirmarCuenta()]" + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean estaLaCuentaConfirmada(String email) {
		Usuario usuarioExistente = repositorio.findFirstByEmail(email);
		if (usuarioExistente != null && usuarioExistente.isCuentaConfirmada()) {
			return true;
		}
		return false;
	}

	/**
	 * Metodo que ejecuta la creacion de un usuario administrador con su rol de
	 * administrador
	 */
	private void inicializarUsuarioAdmin() {
		// Valida si ya se creó un usuario admin
		if (!repositorio.existsByNombreApellidos("admin")) {
			// Si no existe, crea un nuevo usuario con rol de administrador
			Usuario admin = new Usuario();
			admin.setNombreApellidos("admin");
			admin.setPassword(passwordEncoder.encode("admin"));
			admin.setCuentaConfirmada(true);
			admin.setEmail("admin@bikerconnect.com");
			admin.setTelefono("-");
			admin.setRol("ROLE_ADMIN");
			Calendar calendar = Calendar.getInstance();
			admin.setFechaRegistro(calendar);

			repositorio.save(admin);
		}
	}

	/**
	 * Metodo que automatiza la creacion de un usuario administrador que se ejecuta
	 * la primera vez que se despliega la aplicacion
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		inicializarUsuarioAdmin();
	}

	@Override
	public List<UsuarioDTO> obtenerTodos() {
		return toDto.listaUsuarioToDto(repositorio.findAll());
	}

	@Override
	public UsuarioDTO buscarPorId(long id) {
		return toDto.usuarioToDto(repositorio.findById(id).orElse(null));
	}

	@Override
	public UsuarioDTO eliminar(long id) {
		Usuario usuario = repositorio.findById(id).orElse(null);
		if (usuario != null) {
			repositorio.delete(usuario);
		}
		return toDto.usuarioToDto(usuario);

	}

	public void actualizarUsuario(UsuarioDTO usuarioDTO) {

		Usuario existente = repositorio.findById(usuarioDTO.getId()).orElse(null);

		existente.setEmail(usuarioDTO.getEmailUsuario());
		existente.setNombreApellidos(usuarioDTO.getNombreUsuario() + " " + usuarioDTO.getApellidosUsuario());
		existente.setTelefono(usuarioDTO.getTlfUsuario());
		existente.setRol(usuarioDTO.getRol());

		repositorio.save(existente);
	}



}
