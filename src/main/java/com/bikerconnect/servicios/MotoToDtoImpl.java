package com.bikerconnect.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bikerconnect.dtos.MotoDTO;
import com.bikerconnect.entidades.Moto;

/**
 * Servicio que implementa los metodos de la interface {@link IMotoToDto} 
 * y en esta clase es donde se entra al detalle de la logica de los métodos
 * para el paso de la entidad Moto a DTO
 */
@Service
public class MotoToDtoImpl implements IMotoToDto {

	@Override
	public MotoDTO motoToDto(Moto u) {
		
		MotoDTO moto = new MotoDTO();
		
		try {
			moto.setId(u.getIdMoto());
			moto.setMarca(u.getMarca());
			moto.setModelo(u.getModelo());
			moto.setAño(u.getAño());
			moto.setColor(u.getColor());
			moto.setDescModificaciones(u.getDescModificaciones());
		} catch (Exception e) {			
			System.out.println("\n[ERROR MotoToDtoImpl - motoToDto()] - Al convertir entidad Moto a DTO (return null): "+ e);
			return null;
		}
		
		return moto;
	}

	@Override
	public List<MotoDTO> listaMotosToDto(List<Moto> listaMotos) {
		
		List<MotoDTO> listaDto = new ArrayList<MotoDTO>();

		try {
			for (Moto moto : listaMotos) {
				listaDto.add(motoToDto(moto));
			}
			return listaDto;
		} catch (Exception e) {			
			System.out.println("\n[ERROR MotoToDtoImpl - listaMotosToDto()] - Al convertir lista de entidades Moto a DTO (return null): "+ e);
		}
		return null;
	}

}
