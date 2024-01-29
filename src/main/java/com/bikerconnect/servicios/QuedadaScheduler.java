package com.bikerconnect.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Clase que implementa la programación de tareas programadas para comprobar las
 * quedadas "Planificadas" cuya fecha y hora ya han pasado para poder cambiarla a
 * completadas.
 */
@Component
public class QuedadaScheduler {

	@Autowired
	private IQuedadaServicio quedadaServicio;

	@Scheduled(fixedRate = 60000) 
	public void verificarQuedadasCompletadas() {
		quedadaServicio.verificarQuedadasCompletadas();
	}

}
