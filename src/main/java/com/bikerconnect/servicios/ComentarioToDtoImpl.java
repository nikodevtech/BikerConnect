package com.bikerconnect.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.ComentarioDTO;
import com.bikerconnect.entidades.Comentario;

/**
 * Servicio que implementa los metodos de la interface {@link IComentarioToDto}
 * en esta clase es donde se entra al detalle de los métodos
 * para el paso de la entidad Comentario a DTO
 */
@Service
public class ComentarioToDtoImpl implements IComentarioToDto {

	@Override
	public List<ComentarioDTO> listaComentarioToDto(List<Comentario> listaComentarios) {
		
		List<ComentarioDTO> listaDto = new ArrayList<ComentarioDTO>();
		try {
			
			for (Comentario comentario : listaComentarios) {
				listaDto.add(comentarioToDto(comentario));
			}
			return listaDto;
			
		} catch (Exception e) {			
			System.out.println("\n[ERROR ComentarioToDtoImpl - listaComentariosToDto()] - Al convertir lista de entidades Comentario a DTO (return null): "+ e);
		}
		return null;
	}

	@Override
	public ComentarioDTO comentarioToDto(Comentario comentario) {
		
		ComentarioDTO comentarioDTO = new ComentarioDTO();
		
		try {
			comentarioDTO.setIdComentario(comentario.getIdComentario());
			comentarioDTO.setUsuarioAutor(comentario.getUsuarioAutor());
			comentarioDTO.setContenido(comentario.getContenido());
			comentarioDTO.setFechaComentario(comentario.getFechaComentario());
			comentarioDTO.setIdQuedada(comentario.getQuedada().getIdQuedada());
			return comentarioDTO;
		} catch (Exception e) {			
			System.out.println("\n[ERROR ComentarioToDtoImpl - comentarioToDto()] - Al convertir entidad Comentario a DTO (return null): "+ e);
			return null;

		}
	}

}
