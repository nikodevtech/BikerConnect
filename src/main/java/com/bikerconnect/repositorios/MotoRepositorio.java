package com.bikerconnect.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bikerconnect.entidades.Moto;
import com.bikerconnect.entidades.Usuario;

/**
 * Interfaz que define un repositorio para la entidad {@link Moto}. Extiende
 * la interfaz JpaRepository de Spring Data para realizar operaciones CRUD y
 * otras consultas relacionadas con la entidad Moto en la base de datos.
 */
public interface MotoRepositorio extends JpaRepository<Moto, Long> {
	
	/**
	 * Obtener todas las motos de un usuario concreto por su id
	 * @param idUsuario id del usuario a buscar
	 * @return Lista de todas las motos del usuario 
	 */
	@Query("SELECT m FROM Moto m WHERE m.usuarioPropietario.id = :idUsuario")
    List<Moto> findAllByUsuarioPropietarioId(@Param("idUsuario") Long idUsuario);

}
