package com.bikerconnect.dtos;

import java.util.Calendar;
import java.util.Objects;

/**
 * Clase que representa un objeto quedada como DTO para almacenar los datos de quedadas y 
 * moverlo entre las distintas capas de la aplicación.
 */
public class QuedadaDTO {
	
	private long id;
	private String lugar;
	private Calendar fechaHora;
	private String descripcion;
	private String estado;
	private String usuarioOrganizador;
	
	//Getters y Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public Calendar getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Calendar fechaHora) {
		this.fechaHora = fechaHora;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getUsuarioOrganizador() {
		return usuarioOrganizador;
	}
	
	public void setUsuarioOrganizador(String usuarioOrganizador) {
		this.usuarioOrganizador = usuarioOrganizador;
	}
	
	//Metodos
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, estado, fechaHora, id, lugar, usuarioOrganizador);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuedadaDTO other = (QuedadaDTO) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(estado, other.estado)
				&& Objects.equals(fechaHora, other.fechaHora) && id == other.id && Objects.equals(lugar, other.lugar)
				&& Objects.equals(usuarioOrganizador, other.usuarioOrganizador);
	}
	@Override
	public String toString() {
		return "QuedadaDTO [id=" + id + ", lugar=" + lugar + ", fechaHora=" + fechaHora + ", descripcion=" + descripcion
				+ ", estado=" + estado + ", usuarioOrganizador=" + usuarioOrganizador + "]";
	}
		

}
