package com.bikerconnect.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikerconnect.entidades.Comentario;

/**
 * Interfaz que define un repositorio para la entidad {@link Comentario}. Extiende
 * la interfaz JpaRepository de Spring Data para realizar operaciones CRUD y
 * otras consultas relacionadas con la tabla Comentario en la base de datos.
 */
public interface ComentarioRepositorio extends JpaRepository<Comentario, Long> {

	/**
	 * Metodo que busca todos los comentarios de una quedada por el id de la quedada
	 * @param idQuedada El id de la quedada
	 * @return La lista de comentarios de la quedada
	 */
	 List<Comentario> findByQuedadaIdQuedada(Long idQuedada);}
