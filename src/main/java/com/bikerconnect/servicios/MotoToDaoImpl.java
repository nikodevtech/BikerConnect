package com.bikerconnect.servicios;

import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.MotoDTO;
import com.bikerconnect.entidades.Moto;

/**
 * Servicio que implementa los metodos de la interface {@link IMotoToDao} 
 * y en esta clase es donde se entra al detalle de la logica de dichos métodos
 * para el paso de objeto DTO a DAO de moto
 */
@Service
public class MotoToDaoImpl implements IMotoToDao {

	@Override
	public Moto motoToDao(MotoDTO motoDTO) {
		
		try {
			Moto m = new Moto();
			m.setMarca(motoDTO.getMarca());
			m.setModelo(motoDTO.getModelo());
			m.setAño(motoDTO.getAño());
			m.setColor(motoDTO.getColor());
			m.setDescModificaciones(motoDTO.getDescModificaciones());
			
			return m;
			
		} catch(Exception e) {
			System.out.println("\n[ERROR MotoToDaoImpl - motoToDao()] - Al convertir motoDTO a DAO (return null): "+ e);
		}
		
		return null;
	}

}
