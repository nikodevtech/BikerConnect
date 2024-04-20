package com.bikerconnect.servicios;

import com.bikerconnect.dtos.MotoDTO;

/**
 * Interfaz para el servicio de la gestión de las motos, donde se declaran los
 * métodos correspondientes que serán implementados.
 */
public interface IMotoServicio {
	
	/**
	 * Se registra una moto de un usuario en el sistema
	 * @param motoDTO La nueva moto a registrar
	 * @return true si se ha podido registrar, false en caso contrario
	 */
	public boolean registrarMoto(MotoDTO motoDTO);

	/**
	 * Elimina una moto de la base de datos
	 * @param id El ID de la moto a eliminar
	 */
	public void eliminarMoto(long id);
	
	/**
	 * Busca una moto por su ID
	 * @param id El ID de la moto
	 * @return La motoDTO buscada o null si no existe
	 */
	public MotoDTO buscarPorId(long id);
	
	/**
	 * Metodo para actualizar una moto en la base de datos
	 * @param motoDTO La moto a actualizar
	 * @return true si se ha podido actualizar, false en caso contrario
	 */
	public boolean actualizarMoto(MotoDTO motoDTO);
	
}
