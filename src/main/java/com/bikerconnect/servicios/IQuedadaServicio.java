package com.bikerconnect.servicios;

import java.util.List;

import com.bikerconnect.dtos.QuedadaDTO;

import jakarta.persistence.PersistenceException;

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
	 * @return una cadena de texto con la información de la asistencia
	 */
	public String unirseQuedada(Long idQuedada, String emailUsuario);
	
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
	
    /**
     * 
	 * Comprueba los participantes de la quedada".
     * @param idQuedada el id de la quedada
     * @return true si hay participantes o false si no
     */
	public boolean verificarParticipantesQuedada(long idQuedada);
	
	/**
	 * Cancela una quedada
	 * @param idQuedada el id de la quedada a cancelar
	 * @return una cadena de texto con la información sobre la cancelación
	 * @throws IllegalArgumentException
	 */
	public String cancelarQuedada(long idQuedada) throws IllegalArgumentException;
	
	/**
	 * Actualiza el estado de una quedada a completada.
	 * @param idQuedada el id de la quedada
	 */
	public void actualizarQuedada(long idQuedada);
	
	/**
	 * Agrega un comentario a una quedada
	 * @param idQuedada el id de la quedada
	 * @param contenido el contenido del comentario
	 * @param emailUsuario el email del usuario
	 */
	public void agregarComentario(Long idQuedada, String contenido, String emailUsuario);


}
