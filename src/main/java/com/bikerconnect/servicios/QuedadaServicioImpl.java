package com.bikerconnect.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.QuedadaDTO;
import com.bikerconnect.repositorios.QuedadaRepositorio;

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

	@Override
	public List<QuedadaDTO> obtenerQuedadas() {
		return toDto.listaQuedadToDto(quedadaRepo.findAll());
	}

}
