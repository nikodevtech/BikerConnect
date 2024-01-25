package com.bikerconnect.servicios;

import java.util.List;

import com.bikerconnect.dtos.QuedadaDTO;
import com.bikerconnect.entidades.Quedada;

/**
 * Interface donde se declaran los metodos que son necesarios para el paso de una entidad
 * quedada (DAO) a quedadaDTO
 */
public interface IQuedadaToDto {
	
	/**
	 * Método que convierte campo a campo un objeto entidad Quedada a DTO
	 * @param q La quedada a transformar
	 * @return El DTO de la quedada
	 */
	public QuedadaDTO quedadaToDto(Quedada q);
	
	/**
	 * Metodo que convierte todos los objetos entidad Quedada DAO a una lista QuedadaDTO 
	 * @param listaQuedadas La lista de quedadas
	 * @return La lista de quedadas como DTO
	 */
	public List<QuedadaDTO> listaQuedadToDto(List<Quedada> listaQuedadas);

}
