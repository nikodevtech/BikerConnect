package com.bikerconnect.servicios;

import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.QuedadaDTO;
import com.bikerconnect.entidades.Quedada;

/**
 * Servicio que implementa los metodos de la interface {@link IQuedadaToDao} 
 * en esta clase es donde se entra al detalle de la logica de dichos métodos
 * para el paso de quedadaDTO a DAO
 */
@Service
public class QuedadaToDaoImpl implements IQuedadaToDao {

	@Override
	public Quedada quedadaToDao(QuedadaDTO quedadaDTO) {
		try {
			Quedada q = new Quedada();
			q.setLugar(quedadaDTO.getLugar());
			q.setFechaHoraEncuentro(quedadaDTO.getFechaHora());
			q.setUsuarioOrganizador(quedadaDTO.getUsuarioOrganizador());
			q.setDescripcion(quedadaDTO.getDescripcion());
			q.setEstado(quedadaDTO.getEstado());
			
			return q;
			
		} catch(Exception e) {
			System.out.println("\n[ERROR QuedadaToDaoImpl - quedadaToDao()] - Al convertir quedadaDTO a DAO (return null): "+ e);
		}
		
		return null;
	}

}
