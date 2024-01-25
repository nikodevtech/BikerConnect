package com.bikerconnect.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.QuedadaDTO;
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
		QuedadaDTO quedada = new QuedadaDTO();
		
		try {
			quedada.setLugar(q.getLugar());
			quedada.setFechaHora(q.getFechaHoraEncuentro());
			quedada.setId(q.getIdQuedada());
			quedada.setDescripcion(q.getDescripcion());
			quedada.setUsuarioOrganizador(q.getUsuarioOrganizador());
			quedada.setEstado(q.getEstado());
			
			return quedada;
			
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
