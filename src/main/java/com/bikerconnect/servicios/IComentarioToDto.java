package com.bikerconnect.servicios;

import java.util.List;

import com.bikerconnect.dtos.ComentarioDTO;
import com.bikerconnect.entidades.Comentario;

/**
 * Interface donde se declaran los metodos que son necesarios para el paso de una entidad
 * Comentario (DAO) a DTO
 */
public interface IComentarioToDto {
	
	/**
	 * Metodo que convierte una lista de entidades Comentario DAO a una lista ComentarioDTO 
	 * @param listaComentarios La lista de comentarios
	 * @return La lista de comentarios como DTO
	 */
	public List<ComentarioDTO> listaComentarioToDto(List<Comentario> listaComentarios);

	/**
	 * Metodo que convierte una entidad Comentario DAO a una ComentarioDTO
	 * @param comentario La entidad comentario
	 * @return La entidad comentario como DTO
	 */
	public ComentarioDTO comentarioToDto(Comentario comentario);

}
