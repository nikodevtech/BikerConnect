package com.bikerconnect.entidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Clase DAO (Data Access Object) que representa la tabla quedadas de la BBDD,
 * mapea con esta 1:1 y ejerce como modelo virtual de la tabla en la aplicación.
 */
@Entity
@Table(name = "quedadas", schema = "gestion_logicanegocio")
public class Quedada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_quedada")
	private Long idQuedada;

	@Column(name = "desc_quedada", nullable = true, length = 200)
	private String descripcion;

	@Column(name = "fch_hora_encuentro", nullable = false)
	private Calendar fechaHoraEncuentro;

	@Column(nullable = false, length = 150)
	private String lugar;

	@Column(nullable = false, length = 50)
	private String estado;
	
	@Column(name = "usuario_organizador", nullable = false, length = 50)
	private String usuarioOrganizador;

	@ManyToMany
	@JoinTable(name = "participantes", 
			   joinColumns = @JoinColumn(name = "id_quedada"), 
			   inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	private List<Usuario> usuariosParticipantes = new ArrayList<>();
	
	@OneToMany(mappedBy = "quedada")
	private List<Comentario> comentarios = new ArrayList<>();
	
	//Getters y Setters
	public Long getIdQuedada() {
		return idQuedada;
	}

	public void setIdQuedada(Long idQuedada) {
		this.idQuedada = idQuedada;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Calendar getFechaHoraEncuentro() {
		return fechaHoraEncuentro;
	}

	public void setFechaHoraEncuentro(Calendar fechaHoraEncuentro) {
		this.fechaHoraEncuentro = fechaHoraEncuentro;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
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

	public List<Usuario> getUsuariosParticipantes() {
		return usuariosParticipantes;
	}

	public void setUsuariosParticipantes(List<Usuario> usuariosParticipantes) {
		this.usuariosParticipantes = usuariosParticipantes;
	}
	
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	//Metodos
	@Override
	public int hashCode() {
		return Objects.hash(comentarios, descripcion, estado, fechaHoraEncuentro, idQuedada, lugar, usuarioOrganizador,
				usuariosParticipantes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quedada other = (Quedada) obj;
		return Objects.equals(comentarios, other.comentarios) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(estado, other.estado) && Objects.equals(fechaHoraEncuentro, other.fechaHoraEncuentro)
				&& Objects.equals(idQuedada, other.idQuedada) && Objects.equals(lugar, other.lugar)
				&& Objects.equals(usuarioOrganizador, other.usuarioOrganizador)
				&& Objects.equals(usuariosParticipantes, other.usuariosParticipantes);
	}

	@Override
	public String toString() {
		return "Quedada [idQuedada=" + idQuedada + ", descripcion=" + descripcion + ", fechaHoraEncuentro="
				+ fechaHoraEncuentro + ", lugar=" + lugar + ", estado=" + estado + ", usuarioOrganizador="
				+ usuarioOrganizador + ", usuariosParticipantes=" + usuariosParticipantes + ", comentarios="
				+ comentarios + "]";
	}



	
}
