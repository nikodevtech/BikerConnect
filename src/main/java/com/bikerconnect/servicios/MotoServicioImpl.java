package com.bikerconnect.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.MotoDTO;
import com.bikerconnect.entidades.Moto;
import com.bikerconnect.repositorios.MotoRepositorio;

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
	private IMotoToDto toDto;

	@Override
	public List<MotoDTO> obtenerMotosPorIdUsuario(Long idUsuario) {
		
		List<Moto> motos = motoRepositorio.findAllByUsuarioPropietarioId(idUsuario);
	
		return toDto.listaMotosToDto(motos);
	}

}
