package com.bikerconnect.entidades;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Motos", schema = "gestion_logicanegocio")
public class Moto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moto")
    private Long idMoto;

    @Column(name = "marca", nullable = false, length = 20)
    private String marca;

    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    @Column(name = "año", nullable = false)
    private int año;

    @Column(name = "color", nullable = true, length = 20)
    private String color;

    @Column(name = "desc_modificaciones", nullable = true, length = 100)
    private String descModificaciones;

    @ManyToOne
    @JoinColumn(name = "id_usuario_propietario_fk")
    private Usuario usuarioPropietario;
    
    public Moto() {
	    
    }

	public Long getIdMoto() {
		return idMoto;
	}

	public void setIdMoto(Long idMoto) {
		this.idMoto = idMoto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescModificaciones() {
		return descModificaciones;
	}

	public void setDescModificaciones(String descModificaciones) {
		this.descModificaciones = descModificaciones;
	}

	public Usuario getUsuarioPropietario() {
		return usuarioPropietario;
	}

	public void setUsuarioPropietario(Usuario usuarioPropietario) {
		this.usuarioPropietario = usuarioPropietario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(año, color, descModificaciones, idMoto, marca, modelo, usuarioPropietario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moto other = (Moto) obj;
		return año == other.año && Objects.equals(color, other.color)
				&& Objects.equals(descModificaciones, other.descModificaciones) && Objects.equals(idMoto, other.idMoto)
				&& Objects.equals(marca, other.marca) && Objects.equals(modelo, other.modelo)
				&& Objects.equals(usuarioPropietario, other.usuarioPropietario);
	}

	@Override
	public String toString() {
		return "Moto [idMoto=" + idMoto + ", marca=" + marca + ", modelo=" + modelo + ", año=" + año + ", color="
				+ color + ", descModificaciones=" + descModificaciones + ", usuarioPropietario=" + usuarioPropietario
				+ "]";
	}
    
    

}
