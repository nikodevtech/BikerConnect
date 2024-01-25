package com.bikerconnect.servicios;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.MotoDTO;
import com.bikerconnect.entidades.Moto;
import com.bikerconnect.entidades.Usuario;
import com.bikerconnect.repositorios.MotoRepositorio;
import com.bikerconnect.repositorios.UsuarioRepositorio;

import jakarta.persistence.PersistenceException;

/**
 * Servicio que implementa los metodos de la interface {@link IMotoServicio} 
 * y en esta clase es donde se entra al detalle de la logica de dichos métodos
 * para la gestión de las motos de los usuarios.
 */
@Service
public class MotoServicioImpl implements IMotoServicio {

	@Autowired
	private MotoRepositorio motoRepositorio;
	
	@Autowired
	private IMotoToDao toDao;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;


	@Override
	public boolean registrarMoto(MotoDTO motoDTO) {
		
		try {
			Optional<Usuario> usuarioPropietario = usuarioRepositorio.findById(motoDTO.getIdPropietario());
			Moto moto = toDao.motoToDao(motoDTO);
			if(usuarioPropietario.isPresent())
				moto.setUsuarioPropietario(usuarioPropietario.get());
			Moto motoGuardada = motoRepositorio.save(moto);
			
			if(motoGuardada != null) {
				return true;
			} else {
				return false;
			}
		} catch(PersistenceException e) {
			System.out.println("\n[ERROR MotoServicioImpl - registrarMoto()] - Al registrar moto: "+ e);
			return false;
		}	
	}

//	@Override
//	public List<MotoDTO> obtenerMotosPorIdUsuario(Long idUsuario) {
//		
//		List<Moto> motos = motoRepositorio.findAllByUsuarioPropietarioId(idUsuario);
//	
//		return toDto.listaMotosToDto(motos);
//	}

}
