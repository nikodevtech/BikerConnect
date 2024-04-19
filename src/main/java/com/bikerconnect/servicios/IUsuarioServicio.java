package com.bikerconnect.servicios;


import java.util.List;

import com.bikerconnect.dtos.UsuarioDTO;

/**
 * Interfaz del servicio para la gestión de usuarios, donde se declaran los
 * métodos correspondientes que serán implementados.
 */
public interface IUsuarioServicio {
	
	/**
	 * Se registra a un usuario antes comprobando si ya se encuentra en la BBDD registrado el usuario
	 * @param userDTO El usuario a registrar
	 * @return El usuario registrado
	 */
	public UsuarioDTO registrarUsuario(UsuarioDTO userDTO);
	
	/**
	 * Inicia el proceso de recuperacion (generando token y vencimiento) con el email del usuario 
	 * @param emailUsuario El email del usuario que quiere recuperar la contraseña
	 * @return true si el proceso se ha iniciado correctamente, false en caso contrario
	 */
	public boolean iniciarProcesoRecuperacion(String emailUsuario); 
	
	/**
	 * Busca un usuario por su token de recuperación.
	 * @param token que identifica al usuario recibió un correo con la url y dicho token
	 * @return el usuario buscado
	 */
	public UsuarioDTO obtenerUsuarioPorToken(String token);

	
	/**
	 * Establece la nueva contraseña del usuario con el token
	 * @param usuario El usuario al que se le establecera la nueva contraseña
	 * @return true si el proceso se ha realizado correctamente, false en caso contrario
	 */
	public boolean modificarContraseñaConToken(UsuarioDTO usuario);

	
	/**
	 * Metodo que controla que el usuario existe y no esta su cuenta confirmada
	 * @param emailUsuario El email del usuario a confirmar
	 * @return true si el proceso se ha realizado correctamente, false en caso contrario
	 */
	boolean confirmarCuenta(String emailUsuario);
	
	/**
	 * Comprueba si el usuario existe y si su cuenta ha sido confirmada
	 * @param email El email del usuario
	 * @return true si el usuario existe y su cuenta ha sido confirmada, false en caso contrario
	 */
	public boolean estaLaCuentaConfirmada(String email);

	/**
	 * Obtiene la lista de todos los usuarios registrados
	 * @return la lista de todos los usuarios DTO
	 */
	public List<UsuarioDTO> obtenerTodos();
	
	/**
	 * Busca a un usuario por su id asignado en la bbdd
	 * @param id del usuario a buscar
	 * @return El usuario buscado como DTO
	 */
	public UsuarioDTO buscarPorId(long id);
	
	/**
	 * Elimina un usuario por su identificador
	 * @param id del usuario a eliminar
	 */
	public void eliminar(long id);
	
	/**
	 * Modifica un usuario en la bbdd
	 * @param usuario el usuario con los datos modificados
	 */
	public void actualizarUsuario(UsuarioDTO usuarioModificado);

	/**
	 * Busca un usuario por su email
	 * @param email del usuario
	 * @return el usuario buscado como DTO
	 */
	public UsuarioDTO buscarPorEmail(String email);
	
	/**
	 * Cuenta el numero de usuarios que coinciden con el rol dado por parametro
	 * @param rol El rol del usuario
	 * @return el numero de usuarios que coinciden con el rol
	 */
	public int contarUsuariosPorRol(String rol);
	
	/**
	 * Busca a los usuarios que su email contenga la palabra de coincidencia pasada por parametro
	 * @param palabra la palabra de coincidencia
	 * @return la lista de usuarios
	 */
	public List<UsuarioDTO> buscarPorCoincidenciaEnEmail(String palabra);
	
	/**
	 * Busca a los usuarios que su nombre contenga la palabra de coincidencia pasada por parametro
	 * @param palabra la palabra de coincidencia
	 * @return la lista de usuarios
	 */
	public List<UsuarioDTO> buscarPorCoincidenciaEnNombre(String palabra);
	



}
