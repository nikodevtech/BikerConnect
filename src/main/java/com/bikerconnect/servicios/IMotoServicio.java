package com.bikerconnect.servicios;

import java.util.List;

import com.bikerconnect.dtos.MotoDTO;

/**
 * Interfaz para el servicio de la gestión de las motos, donde se declaran los
 * métodos correspondientes que serán implementados.
 */
public interface IMotoServicio {
	
	/**
	 * Obtiene una lista de motos de un usuario
	 * @param idUsuario El id del usuario que tiene las motos
	 * @return La lista de motos
	 */
	public List<MotoDTO> obtenerMotosPorIdUsuario(Long idUsuario);

}
