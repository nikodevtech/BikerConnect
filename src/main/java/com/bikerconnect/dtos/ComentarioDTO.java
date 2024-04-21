package com.bikerconnect.dtos;

import java.util.Calendar;
import java.util.Objects;

/**
 * Clase DTO que representa un objeto Comentario para almacenar sus datos y
 * moverlo entre las distintas capas de la aplicación.
 */
public class ComentarioDTO {

	// Atributos
	private long idComentario;
	private String usuarioAutor;
	private String contenido;
	private Calendar fechaComentario;
	private long idQuedada;

	// Constructor
	public ComentarioDTO() {
	}

	// Getters y Setters
	public long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(long idComentario) {
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

	public long getIdQuedada() {
		return idQuedada;
	}

	public void setIdQuedada(long idQuedada) {
		this.idQuedada = idQuedada;
	}

	// Metodos
	@Override
	public int hashCode() {
		return Objects.hash(contenido, fechaComentario, idComentario, idQuedada, usuarioAutor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComentarioDTO other = (ComentarioDTO) obj;
		return Objects.equals(contenido, other.contenido) && Objects.equals(fechaComentario, other.fechaComentario)
				&& idComentario == other.idComentario && idQuedada == other.idQuedada
				&& Objects.equals(usuarioAutor, other.usuarioAutor);
	}

	@Override
	public String toString() {
		return "ComentarioDTO [idComentario=" + idComentario + ", usuarioAutor=" + usuarioAutor + ", contenido="
				+ contenido + ", fechaComentario=" + fechaComentario + ", idQuedada=" + idQuedada + "]";
	}

	

}
