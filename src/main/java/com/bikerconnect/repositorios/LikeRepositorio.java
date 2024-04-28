package com.bikerconnect.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bikerconnect.entidades.Comentario;
import com.bikerconnect.entidades.Like;

/**
 * Interfaz que define un repositorio para la entidad {@link Like}. Extiende
 * la interfaz JpaRepository de Spring Data para realizar operaciones CRUD y
 * otras consultas relacionadas con la tabla Likes en la base de datos.
 */
public interface LikeRepositorio extends JpaRepository<Like, Long> {

	/**
	 * Devuelve el numero de likes de un comentario
	 * @param idComentario el id del comentario
	 * @return el numero de likes
	 */
	@Query("SELECT COUNT(l) FROM Like l WHERE l.comentario.idComentario = :idComentario")
	long contarLikesPorComentario(@Param("idComentario") Long idComentario);
	
}
