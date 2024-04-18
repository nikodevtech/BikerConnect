package com.bikerconnect.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bikerconnect.entidades.Quedada;

/**
 * Interfaz que define un repositorio para la entidad {@link Quedada}. Extiende
 * la interfaz JpaRepository de Spring Data para realizar operaciones CRUD y
 * otras consultas relacionadas con la tabla Quedada en la base de datos.
 */
public interface QuedadaRepositorio extends JpaRepository<Quedada, Long> {
	
}
