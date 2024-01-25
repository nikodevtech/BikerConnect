package com.bikerconnect.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.MotoDTO;
import com.bikerconnect.dtos.UsuarioDTO;
import com.bikerconnect.entidades.Usuario;
import com.bikerconnect.repositorios.UsuarioRepositorio;

import jakarta.persistence.PersistenceException;
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
	
	@Autowired
	private IMotoToDto toMotoDto;

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
			System.out.println("[Error UsuarioServicioImpl - registrarUsuario()] Argumento no valido al registrar usuario " + iae.getMessage());
		} catch (PersistenceException e) {
			System.out.println("[Error UsuarioServicioImpl - registrarUsuario()] Error de persistencia al registrar usuario " + e.getMessage());
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
				System.out.println("El usuario con email "+ emailUsuario + " no existe");
				return false;
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error UsuarioServicioImpl - iniciarProcesoRecuperacion()] Argumento no valido al iniciar el proceso de recuperación" + iae.getMessage());
			return false;
		} catch (PersistenceException e) {
			System.out.println("[Error UsuarioServicioImpl - iniciarProcesoRecuperacion()] Error de persistencia al iniciar el proceso de recuperación de contraseña" + e.getMessage());
			return false;
		}
	}

	@Override
	public UsuarioDTO obtenerUsuarioPorToken(String token) {
		try {
			Usuario usuarioExistente = repositorio.findByToken(token);

			if (usuarioExistente != null) {
				UsuarioDTO usuario = toDto.usuarioToDto(usuarioExistente);
				return usuario;
			} else {
				System.out.println("No existe el usuario con el token " + token);
				return null;
			}
		} catch (Exception e) {
			System.out.println("[Error UsuarioServicioImpl - obtenerUsuarioPorToken()] Error al obtener usuario por token " + e.getMessage());
			return null;
		}
	}

	@Override
	public boolean modificarContraseñaConToken(UsuarioDTO usuario) {
		try {
			Usuario usuarioExistente = repositorio.findByToken(usuario.getToken());

			if (usuarioExistente != null) {
				String nuevaContraseña = passwordEncoder.encode(usuario.getPassword());
				usuarioExistente.setPassword(nuevaContraseña);
				usuarioExistente.setToken(null); // Se setea a null para invalidar el token ya consumido al cambiar de
													// password
				repositorio.save(usuarioExistente);

				return true;
			}

		} catch (Exception e) {
			System.out.println("[Error UsuarioServicioImpl - modificarContraseñaConToken()] Error al modificar el password con el token " + e.getMessage());
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
				System.out.println("La cuenta no existe o ya está confirmada");
				return false;
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error UsuarioServicioImpl - confirmarCuenta()] Error al confirmar la cuenta " + iae.getMessage());
			return false;
		} catch (PersistenceException e) {
			System.out.println("[Error UsuarioServicioImpl - confirmarCuenta()] Error de persistencia al confirmar la cuenta" + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean estaLaCuentaConfirmada(String email) {
		try {
			Usuario usuarioExistente = repositorio.findFirstByEmail(email);
			if (usuarioExistente != null && usuarioExistente.isCuentaConfirmada()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("[Error UsuarioServicioImpl - estaLaCuentaConfirmada()] Error al comprobar si la cuenta ya ha sido confirmada" + e.getMessage());
		}	
		return false;
	}

	/**
	 * Metodo que ejecuta la creacion de un usuario administrador con su rol de
	 * administrador
	 */
	private void inicializarUsuarioAdmin() {
		try {
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
		} catch (PersistenceException e) {
			System.out.println("[Error UsuarioServicioImpl - inicializarUsuarioAdmin()] Error de persistencia al inicializar el usuario administrador" + e.getMessage());
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
		try {
			Usuario usuario = repositorio.findById(id).orElse(null);
			if (usuario != null) {
				return toDto.usuarioToDto(usuario);
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error UsuarioServicioImpl - buscarPorId()] Al buscar el usuario por su id " + iae.getMessage());
		}
		return null;
	}

	@Override
	public void eliminar(long id) {
		try {
			Usuario usuario = repositorio.findById(id).orElse(null);
			if (usuario != null) {
				repositorio.delete(usuario);
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error UsuarioServicioImpl - eliminar()] Al eliminar el usuario por su id " + iae.getMessage());
		} 
	}

	@Override
	public void actualizarUsuario(UsuarioDTO usuarioModificado) {

		try {
			Usuario usuarioActual = repositorio.findById(usuarioModificado.getId()).orElse(null);

			usuarioActual.setEmail(usuarioModificado.getEmailUsuario());
			usuarioActual.setNombreApellidos(usuarioModificado.getNombreUsuario() + " " + usuarioModificado.getApellidosUsuario());
			usuarioActual.setTelefono(usuarioModificado.getTlfUsuario());
			usuarioActual.setRol(usuarioModificado.getRol());
			usuarioActual.setCuentaConfirmada(usuarioModificado.isCuentaConfirmada());
			usuarioActual.setPassword(passwordEncoder.encode(usuarioModificado.getClaveUsuario()));

			repositorio.save(usuarioActual);
		} catch (PersistenceException pe) {
			System.out.println("[Error UsuarioServicioImpl - actualizarUsuario()] Al modificar el usuario " + pe.getMessage());
			
		}
		
	}
	
	@Override
	public UsuarioDTO buscarPorEmail(String email) {
		try {
			List<MotoDTO> motos = new ArrayList<>();
			UsuarioDTO uDto = new UsuarioDTO();
			Usuario usuario = repositorio.findFirstByEmail(email);
			motos = toMotoDto.listaMotosToDto(usuario.getMotosPropias());
			uDto = toDto.usuarioToDto(usuario);
			uDto.setMisMotos(motos);
			
			if (usuario != null) {
				return uDto;
			}
		} catch (Exception e) {
			System.out.println("[Error UsuarioServicioImpl - buscarPorEmail()] Al buscar el usuario por su email " + e.getMessage());
		}	
		return null;
	}



}
