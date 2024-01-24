package com.bikerconnect.servicios;

import java.util.List;

import com.bikerconnect.dtos.MotoDTO;
import com.bikerconnect.entidades.Moto;

/**
 * Interface donde se declaran los metodos necesarios para el paso de una entidad Moto a DTO
 */
public interface IMotoToDto {

	/**
	 * Método que convierte campo a campo un objeto entidad Usuario a usuarioDTO
	 * @param u La moto a pasar a DTO
	 * @return El DTO del usuario
	 */
	public MotoDTO motoToDto(Moto u);
	
	/**
	 * Metodo que convierte todos los objetos entidad Moto a una lista motos como DTO
	 * @param listaMotos La lista de motos DAO
	 * @return La lista de motos como DTO
	 */
	public List<MotoDTO> listaMotosToDto(List<Moto> listaMotos);
}
