package com.bikerconnect.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.QuedadaDTO;
import com.bikerconnect.entidades.Quedada;
import com.bikerconnect.entidades.Usuario;
import com.bikerconnect.repositorios.QuedadaRepositorio;
import com.bikerconnect.repositorios.UsuarioRepositorio;

import jakarta.persistence.PersistenceException;

/**
 * Servicio que implementa los metodos de la interface {@link IQuedadaServicio} 
 * y en esta clase es donde se entra al detalle de la logica de dichos métodos
 * para la gestión de las quedadas.
 */
@Service
public class QuedadaServicioImpl implements IQuedadaServicio {
	
	@Autowired
	private QuedadaRepositorio quedadaRepo;
	
	@Autowired
	private IQuedadaToDto toDto;
	
	@Autowired
	private IQuedadaToDao toDao;
	
	@Autowired
	private UsuarioRepositorio usuarioRepo;

	@Override
	public List<QuedadaDTO> obtenerQuedadas() {
		return toDto.listaQuedadToDto(quedadaRepo.findAll());
	}

	@Override
	public boolean crearQuedada(QuedadaDTO quedadaDTO) {
		try {
			quedadaRepo.save(toDao.quedadaToDao(quedadaDTO));
			return true;
		} catch(PersistenceException e) {
			System.out.println("\n[ERROR QuedadaServicioImpl - crearQuedada()] - Al registrar nueva quedada: "+ e);
			return false;
		}
	}

	@Override
	public QuedadaDTO obtenerQuedadaPorId(Long id) {
		try {
			QuedadaDTO quedada = new QuedadaDTO();
			quedada = toDto.quedadaToDto(quedadaRepo.findById(id).get());
			return quedada;
		} catch (IllegalArgumentException e) {
			System.out.println("\n[ERROR QuedadaServicioImpl - otenerQuedadaPorId()] - Al buscar una quedada por su id: "+ e);
		}
		return null;
	}

	@Override
	public boolean unirseQuedada(Long idQuedada, String emailUsuario) {
	    try {
	        Quedada q = quedadaRepo.findById(idQuedada).orElse(null);
	        Usuario u = usuarioRepo.findFirstByEmail(emailUsuario);

	        if (u == null || q == null) {
	            return false;
	        }

	        if (u.getQuedadasParticipante().contains(q) || q.getUsuariosParticipantes().contains(u)) {
	            return false; 
	        }

	        u.getQuedadasParticipante().add(q);
	        q.getUsuariosParticipantes().add(u);

	        usuarioRepo.save(u);
	        quedadaRepo.save(q);

	        return true;

	    } catch (IllegalArgumentException e) {
	        System.out.println("\n[ERROR QuedadaServicioImpl - unirseQuedada()] - Error de argumento incorrecto al unirse a una quedada: " + e);
	        return false;
	    } catch (PersistenceException e) {
	        System.out.println("\n[ERROR QuedadaServicioImpl - unirseQuedada()] - Error de persistencia al unirse a una quedada: " + e);
	        return false;
	    }
	}

	
	@Override
	public boolean estaUsuarioUnido(Long idQuedada, String emailUsuario) {
	    Quedada q = quedadaRepo.findById(idQuedada).orElse(null);
	    Usuario u = usuarioRepo.findFirstByEmail(emailUsuario);

	    return (q != null && u != null && (u.getQuedadasParticipante().contains(q) || q.getUsuariosParticipantes().contains(u)));
	}

		

}
