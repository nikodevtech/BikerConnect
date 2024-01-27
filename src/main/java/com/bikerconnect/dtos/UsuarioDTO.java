package com.bikerconnect.dtos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * Clase DTO (Data Transfer Object) para pasar información entre capas para la
 * gestión de usuarios
 */
public class UsuarioDTO {

	// ATRIBUTOS
	private long id;
	private String nombreUsuario;
	private String apellidosUsuario;
	private String tlfUsuario;
	private String emailUsuario;
	private String claveUsuario;
	private String token;
	private String password;
	private String password2;
	private Calendar expiracionToken;
	private Calendar fechaRegistro;
	private boolean cuentaConfirmada;
	private String rol;
	private List<MotoDTO> misMotos = new ArrayList<>();

	// CONSTRUCTORES
	public UsuarioDTO() {
	}

	public UsuarioDTO(String nombreUsuario, String apellidosUsuario, String tlfUsuario, String emailUsuario,
			String claveUsuario) {
		this.nombreUsuario = nombreUsuario;
		this.apellidosUsuario = apellidosUsuario;
		this.tlfUsuario = tlfUsuario;
		this.emailUsuario = emailUsuario;
		this.claveUsuario = claveUsuario;
	}

	// GETTERS Y SETTERS
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellidosUsuario() {
		return apellidosUsuario;
	}

	public void setApellidosUsuario(String apellidosUsuario) {
		this.apellidosUsuario = apellidosUsuario;
	}

	public String getTlfUsuario() {
		return tlfUsuario;
	}

	public void setTlfUsuario(String tlfUsuario) {
		this.tlfUsuario = tlfUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getClaveUsuario() {
		return claveUsuario;
	}

	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public Calendar getExpiracionToken() {
		return expiracionToken;
	}

	public void setExpiracionToken(Calendar expiracionToken) {
		this.expiracionToken = expiracionToken;
	}

	public Calendar getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Calendar fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public boolean isCuentaConfirmada() {
		return cuentaConfirmada;
	}

	public void setCuentaConfirmada(boolean cuentaConfirmada) {
		this.cuentaConfirmada = cuentaConfirmada;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public List<MotoDTO> getMisMotos() {
		return misMotos;
	}
	
	public void setMisMotos(List<MotoDTO> misMotos) {
		this.misMotos = misMotos;
	}

	// METODOS
	@Override
	public int hashCode() {
		return Objects.hash(apellidosUsuario, claveUsuario, cuentaConfirmada, emailUsuario, expiracionToken,
				fechaRegistro, id, misMotos, nombreUsuario, password, password2, rol, tlfUsuario, token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioDTO other = (UsuarioDTO) obj;
		return Objects.equals(apellidosUsuario, other.apellidosUsuario)
				&& Objects.equals(claveUsuario, other.claveUsuario) && cuentaConfirmada == other.cuentaConfirmada
				&& Objects.equals(emailUsuario, other.emailUsuario)
				&& Objects.equals(expiracionToken, other.expiracionToken)
				&& Objects.equals(fechaRegistro, other.fechaRegistro) && id == other.id
				&& Objects.equals(misMotos, other.misMotos) && Objects.equals(nombreUsuario, other.nombreUsuario)
				&& Objects.equals(password, other.password) && Objects.equals(password2, other.password2)
				&& Objects.equals(rol, other.rol) && Objects.equals(tlfUsuario, other.tlfUsuario)
				&& Objects.equals(token, other.token);
	}

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", nombreUsuario=" + nombreUsuario + ", apellidosUsuario=" + apellidosUsuario
				+ ", tlfUsuario=" + tlfUsuario + ", emailUsuario=" + emailUsuario + ", claveUsuario=" + claveUsuario
				+ ", token=" + token + ", password=" + password + ", password2=" + password2 + ", expiracionToken="
				+ expiracionToken + ", fechaRegistro=" + fechaRegistro + ", cuentaConfirmada=" + cuentaConfirmada
				+ ", rol=" + rol + "]";
	}
	

}
