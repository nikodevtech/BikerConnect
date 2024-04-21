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
 * Clase DAO (Data Access Object) que representa la tabla comentarios de la
 * BBDD, mapea con esta 1:1 y ejerce como modelo virtual de la tabla en la
 * aplicación.
 */
@Entity
@Table(name = "Comentarios", schema = "gestion_logicanegocio")
public class Comentario {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comentario")
	private Long idComentario;

	@Column(name = "usuario_autor", nullable = false, length = 50)
	private String usuarioAutor;

	@Column(name = "contenido", nullable = false)
	private String contenido;

	@Column(name = "fch_hora_creacion", nullable = true, updatable = false)
	private Calendar fechaComentario;

	@ManyToOne
	@JoinColumn(name = "id_quedada")
	private Quedada quedada;
	
	
	//CONSTRUCTORES
	public Comentario() {
		
	}

	//GETTERS Y SETTERS
	public Long getIdComentario() {
		return idComentario;
	}


	public void setIdComentario(Long idComentario) {
		this.idComentario = idComentario;
	}


	public String getUsuarioAutor() {
		return usuarioAutor;
	}


	public void setUsuarioAutor(String usuarioAutor) {
		this.usuarioAutor = usuarioAutor;
	}


	public String getContenido() {
		return contenido;
	}


	public void setContenido(String contenido) {
		this.contenido = contenido;
	}


	public Calendar getFechaComentario() {
		return fechaComentario;
	}


	public void setFechaComentario(Calendar fechaComentario) {
		this.fechaComentario = fechaComentario;
	}


	public Quedada getQuedada() {
		return quedada;
	}


	public void setQuedada(Quedada quedada) {
		this.quedada = quedada;
	}

	//Metodos
	@Override
	public int hashCode() {
		return Objects.hash(contenido, fechaComentario, idComentario, quedada, usuarioAutor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		return Objects.equals(contenido, other.contenido) && Objects.equals(fechaComentario, other.fechaComentario)
				&& Objects.equals(idComentario, other.idComentario) && Objects.equals(quedada, other.quedada)
				&& Objects.equals(usuarioAutor, other.usuarioAutor);
	}
	
	@Override
	public String toString() {
		return "Comentario [idComentario=" + idComentario + ", usuarioAutor=" + usuarioAutor + ", contenido="
				+ contenido + ", fechaComentario=" + fechaComentario + ", quedada=" + quedada + "]";
	}
	
	

}
