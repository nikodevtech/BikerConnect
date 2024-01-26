package com.bikerconnect.servicios;

import com.bikerconnect.dtos.QuedadaDTO;
import com.bikerconnect.entidades.Quedada;

/**
 * Interface donde se declaran los metodos que serán necesarios implementar
 * para el paso de una QuedadaDTO a DAO
 */
public interface IQuedadaToDao {
	
	/**
	 * Metodo que convierte campo a campo un objeto QuedadaDTO a DAO
	 * @param QuedadaDTO el objeto quedadaDTO
	 * @return Quedada quedadto convertido como DAO
	 */
	public Quedada quedadaToDao(QuedadaDTO quedadaDTO);

}
