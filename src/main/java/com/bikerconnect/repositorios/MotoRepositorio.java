package com.bikerconnect.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bikerconnect.entidades.Moto;

public interface MotoRepositorio extends JpaRepository<Moto, Long> {
	
	@Query("SELECT m FROM Moto m WHERE m.usuarioPropietario.id = :idUsuario")
    List<Moto> findAllByUsuarioPropietarioId(@Param("idUsuario") Long idUsuario);

}
