package com.bikerconnect.servicios;

import java.util.List;

import com.bikerconnect.dtos.QuedadaDTO;

/**
 * Interface donde se declaran los metodos necesarios para la gestión de las quedadas.
 */
public interface IQuedadaServicio {
	
	/**
	 * Metodo que obtiene todas las quedadas de la base de datos
	 * @return La lista de quedadas como DTO
	 */
	public List<QuedadaDTO> obtenerQuedadas();

}
