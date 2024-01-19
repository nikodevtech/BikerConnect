package com.bikerconnect.servicios;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
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
			repositorio.save(usuarioDao);

			return userDto;
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error UsuarioServicioImpl - registrarUsuario() ]" + iae.getMessage());	
		} catch (Exception e) {
			System.out.println("[Error UsuarioServicioImpl - registrarUsuario() ]" + e.getMessage());
		}
		return null;
	}

	

	
}
