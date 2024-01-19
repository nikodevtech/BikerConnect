package com.bikerconnect.entidades;

import java.util.Calendar;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase DAO (Data Access Object) que representa la tabla usuarios de la BBDD,
 * mapea con esta 1:1 y ejerce como modelo virtual de la tabla en la aplicación.
 */
@Entity
@Table(name = "usuarios", schema = "gestion_usuarios")
public class Usuario {
	
	
	//ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", nullable = false)
	private long id;
	
	@Column(name = "nombre_apellidos", nullable = false, length = 50)
	private String nombreApellidos;
	
	@Column(nullable = false, unique = true, length = 100)
	private String email;
	
	@Column(name = "contraseña", nullable = false, length = 100)
	private String password;
	
	@Column(name = "tlf_movil", nullable = false, length = 9)
	private String telefono;
	
	@Column(name = "token_recuperacion", nullable = true, length = 100)
	private String token;
	
	@Column(name = "fch_expiracion_token", nullable = true)
	private Calendar expiracionToken;
	
	@Column(name = "fch_registro", nullable = true, updatable = false)
	private Calendar fechaRegistro;
	
	@Column(name = "cuenta_confirmada", nullable = false, columnDefinition = "boolean default false")
	private boolean cuentaConfirmada;
	
	@Column(nullable = true, length = 20)
	private String rol;
	
	@Column(nullable = true, length = 100)
	private String foto;
	
	
	//CONSTRUCTORES
	public Usuario() {
		
	}

	
	//GETTERS Y SETTERS
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreApellidos() {
		return nombreApellidos;
	}

	public void setNombreApellidos(String nombreApellidos) {
		this.nombreApellidos = nombreApellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	//METODOS
	@Override
	public int hashCode() {
		return Objects.hash(cuentaConfirmada, email, expiracionToken, fechaRegistro, foto, id, nombreApellidos,
				password, rol, telefono, token);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return cuentaConfirmada == other.cuentaConfirmada && Objects.equals(email, other.email)
				&& Objects.equals(expiracionToken, other.expiracionToken)
				&& Objects.equals(fechaRegistro, other.fechaRegistro) && Objects.equals(foto, other.foto)
				&& id == other.id && Objects.equals(nombreApellidos, other.nombreApellidos)
				&& Objects.equals(password, other.password) && Objects.equals(rol, other.rol)
				&& Objects.equals(telefono, other.telefono) && Objects.equals(token, other.token);
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreApellidos=" + nombreApellidos + ", email=" + email + ", password="
				+ password + ", telefono=" + telefono + ", token=" + token + ", expiracionToken=" + expiracionToken
				+ ", fechaRegistro=" + fechaRegistro + ", cuentaConfirmada=" + cuentaConfirmada + ", rol=" + rol
				+ ", foto=" + foto + "]";
	}
	
	

}
