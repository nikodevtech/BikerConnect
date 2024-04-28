package com.bikerconnect.entidades;

import java.util.Calendar;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Clase DAO (Data Access Object) que representa la tabla Likes de la
 * BBDD, mapea con esta 1:1 y ejerce como modelo virtual de la tabla en la
 * aplicación.
 */
@Entity
@Table(name = "Likes", schema = "gestion_logicanegocio")
public class Like {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_like")
	private Long idLike;

	@Column(name = "email_usuario_autor", nullable = false)
	private String usuarioAutor;

	@Column(name = "fch_hora", nullable = true, updatable = false)
	private Calendar fechaHoraLike;

	@ManyToOne
	@JoinColumn(name = "id_comentario")
	private Comentario comentario;

	// CONSTRUCTORES
	public Like() {
		
	}

	// GETTERS Y SETTERS
	public Long getIdLike() {
		return idLike;
	}

	public void setIdLike(Long idLike) {
		this.idLike = idLike;
	}

	public String getUsuarioAutor() {
		return usuarioAutor;
	}

	public void setUsuarioAutor(String usuarioAutor) {
		this.usuarioAutor = usuarioAutor;
	}

	public Calendar getFechaHoraLike() {
		return fechaHoraLike;
	}

	public void setFechaHoraLike(Calendar fechaHoraLike) {
		this.fechaHoraLike = fechaHoraLike;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	// METODOS
	@Override
	public int hashCode() {
		return Objects.hash(comentario, fechaHoraLike, idLike, usuarioAutor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Like other = (Like) obj;
		return Objects.equals(comentario, other.comentario) && Objects.equals(fechaHoraLike, other.fechaHoraLike) 
				&& Objects.equals(idLike, other.idLike) && Objects.equals(usuarioAutor, other.usuarioAutor);
	}

	@Override
	public String toString() {
		return "Like [idLike=" + idLike + ", usuarioAutor=" + usuarioAutor + ", fechaHoraLike=" + fechaHoraLike + ", comentario=" + comentario + "]";
	}
	
	
	
	

}
