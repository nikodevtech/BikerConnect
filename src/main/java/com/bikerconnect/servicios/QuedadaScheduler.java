package com.bikerconnect.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Clase que implementa la programación de tareas automatica para cambiar el estado 
 * de las quedadas "Planificadas" a "Completadas" 
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
