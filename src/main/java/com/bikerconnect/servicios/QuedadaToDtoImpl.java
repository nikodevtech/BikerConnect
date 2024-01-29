package com.bikerconnect.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.QuedadaDTO;
import com.bikerconnect.dtos.UsuarioDTO;
import com.bikerconnect.entidades.Quedada;

/**
 * Servicio que implementa los metodos de la interface {@link IQuedadaToDto} 
 * y en esta clase es donde se entra al detalle de la logica de los métodos
 * para el paso de la entidad Quedada a DTO
 */
@Service
public class QuedadaToDtoImpl implements IQuedadaToDto {
	

	@Override
	public QuedadaDTO quedadaToDto(Quedada q) {
		QuedadaDTO quedadaDTO = new QuedadaDTO();
		
		try {
			quedadaDTO.setLugar(q.getLugar());
			quedadaDTO.setFechaHora(q.getFechaHoraEncuentro());
			quedadaDTO.setId(q.getIdQuedada());
			quedadaDTO.setDescripcion(q.getDescripcion());
			quedadaDTO.setUsuarioOrganizador(q.getUsuarioOrganizador());
			quedadaDTO.setEstado(q.getEstado());
				
			return quedadaDTO;
			
		} catch (Exception e) {			
			System.out.println("\n[ERROR QuedadaToDtoImpl - quedadaToDto()] - Al convertir entidad Quedada a DTO (return null): "+ e);
			return null;
		}
		
	}

	@Override
	public List<QuedadaDTO> listaQuedadToDto(List<Quedada> listaQuedadas) {
		
		try {			
			List<QuedadaDTO> listaQuedadasDTO = new ArrayList<>();
			
			listaQuedadas.forEach(q -> {
				listaQuedadasDTO.add(quedadaToDto(q));
			});
			
			return listaQuedadasDTO;			
		} catch (Exception e) {
			System.out.println("\n[ERROR QuedadaToDtoImpl - listaQuedadToDto()] - Al convertir lista Quedada a DTO (return null): "+ e);
			return null;
		}
	}

}
