package com.bikerconnect.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bikerconnect.servicios.IQuedadaServicio;

/**
 * Clase que ejerce de controlador de la vista de quedadas
 */
@Controller
public class QuedadasControlador {
	
	@Autowired
	private IQuedadaServicio quedadaServicio;
	
	/**
	 * Gestion las peticion HTTP GET para la url /privada/quedadas
	 * @param model El modelo en el que se guarda lista quedadas DTO para la vista
	 * @return Muestra la vista de quedadas.
	 */
	@GetMapping("/privada/quedadas")
	public String quedadas(Model model) {
		model.addAttribute("quedadas", quedadaServicio.obtenerQuedadas());
		return "quedadas";
	}
	
	/**
	 * Gestion las peticion HTTP GET para la url /bienvenida y /
	 * @return Muestra la vista de bienvenida (index.html).
	 */
	@GetMapping("/privada/quedadas/planificar-quedada")
	public String planificarQuedada() {
		return "crearQuedada";
	}

}
