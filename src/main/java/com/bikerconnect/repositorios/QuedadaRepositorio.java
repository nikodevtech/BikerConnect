package com.bikerconnect.repositorios;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikerconnect.entidades.Quedada;

/**
 * Interfaz que define un repositorio para la entidad {@link Quedada}. Extiende
 * la interfaz JpaRepository de Spring Data para realizar operaciones CRUD y
 * otras consultas relacionadas con la tabla Quedada en la base de datos.
 */
public interface QuedadaRepositorio extends JpaRepository<Quedada, Long> {
	
	/**
	 * Obtiene todas las quedadas "Planificadas" cuya fecha y hora ya han pasado 
	 * para poder cambiarla a completada
	 * @param estado el estado de la quedada "Planificada"
	 * @param fecha la fecha actual
	 * @return la lista de quedadas cuya fecha y hora ya pasaron de la actual
	 */
	 public List<Quedada> findByEstadoAndFechaHoraEncuentroBefore(String estado, Calendar fecha);

}
