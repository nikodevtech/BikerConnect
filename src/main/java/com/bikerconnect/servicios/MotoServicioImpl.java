package com.bikerconnect.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.MotoDTO;
import com.bikerconnect.entidades.Moto;
import com.bikerconnect.repositorios.MotoRepositorio;

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
