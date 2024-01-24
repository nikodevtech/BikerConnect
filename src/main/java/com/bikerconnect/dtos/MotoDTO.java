package com.bikerconnect.dtos;

import java.util.Objects;

public class MotoDTO {
	
	private long id;
	private String marca;
	private String modelo;
	private String color;
	private String año;
	private String descModificaciones;
	
	public MotoDTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAño() {
		return año;
	}

	public void setAño(String año) {
		this.año = año;
	}

	public String getDescModificaciones() {
		return descModificaciones;
	}

	public void setDescModificaciones(String descModificaciones) {
		this.descModificaciones = descModificaciones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(año, color, descModificaciones, id, marca, modelo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MotoDTO other = (MotoDTO) obj;
		return Objects.equals(año, other.año) && Objects.equals(color, other.color)
				&& Objects.equals(descModificaciones, other.descModificaciones) && id == other.id
				&& Objects.equals(marca, other.marca) && Objects.equals(modelo, other.modelo);
	}

	@Override
	public String toString() {
		return "MotoDTO [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", color=" + color + ", año=" + año
				+ ", descModificaciones=" + descModificaciones + "]";
	}


}
