package com.bikerconnect.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.QuedadaDTO;
import com.bikerconnect.repositorios.QuedadaRepositorio;

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

}
