package com.bikerconnect.servicios;

import java.util.List;

import com.bikerconnect.dtos.QuedadaDTO;

/**
 * Interface donde se declaran los metodos necesarios para la gestión de las quedadas.
 */
public interface IQuedadaServicio {
	
	/**
	 * Metodo que obtiene todas las quedadas de la base de datos
	 * @return La lista de quedadas como DTO
	 */
	public List<QuedadaDTO> obtenerQuedadas();
	
	/**
	 * Metodo que planifica una nueva quedada
	 * @param quedadaDTO la quedada a planificar
	 * @return true si se pudo crear la quedada o false si no
	 */
	public boolean crearQuedada(QuedadaDTO quedadaDTO);
	
	/**
	 * Busca una quedada por su id
	 * @param id el id de la quedada
	 * @return la quedada como DTO
	 */
	public QuedadaDTO obtenerQuedadaPorId(Long id);
	
	/**
	 * Método que permite unirse a un usuario a una quedada
	 * @param id el id de la quedada
	 * @param emailUsuario el email del usuario
	 * @return true si se pudo unir o false si no
	 */
	public boolean unirseQuedada(Long idQuedada, String emailUsuario);
	
	/**
	 * Método que comprueba si un usuario ya se encuentra unido a una quedada o viceversa
	 * @param idQuedada el id de la quedada
	 * @param emailUsuario el email del usuario
	 * @return true si el usuario ya se encuentra unido o false si no
	 */
	public boolean estaUsuarioUnido(Long idQuedada, String emailUsuario);

	/**
	 * Cancela la asistencia a una quedada de un usuario
	 * @param idQuedada el id de la quedada 
	 * @param emailUsuario el email del usuario
	 * @return true si se pudo cancelar la asistencia o false si no
	 */
	public boolean cancelarAsistenciaQuedada(Long idQuedada, String emailUsuario);

}
