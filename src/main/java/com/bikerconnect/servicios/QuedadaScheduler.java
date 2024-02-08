package com.bikerconnect.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Clase que implementa la programación de tareas automática para cambiar el estado 
 * de las quedadas "Planificadas" a "Completada" o "Cancelada"
 */
@Component
public class QuedadaScheduler {

	@Autowired
	private IQuedadaServicio quedadaServicio;

	@Scheduled(fixedRate = 60000) 
	public void verificarQuedadasCompletadasYcanceladas() {
		quedadaServicio.verificarQuedadas();
	}

}
