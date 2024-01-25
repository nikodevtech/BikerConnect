package com.bikerconnect.servicios;


import com.bikerconnect.dtos.MotoDTO;
import com.bikerconnect.entidades.Moto;

/**
 * Interface donde se declaran los metodos que serán necesarios implementar para el paso de una MotoDTO a DAO
 */
public interface IMotoToDao {

	/**
	 * Metodo que convierte campo a campo un objeto MotoDTO a DAO
	 * @param motoDTO el objeto motooDTO
	 * @return Moto motodto convertido como DAO
	 */
	public Moto motoToDao(MotoDTO motoDTO);
	

}
