package com.bikerconnect.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bikerconnect.repositorios.QuedadaRepositorio;

/**
 * Clase que ejerce de controlador de la vista de quedadas
 */
@Controller
public class QuedadasControlador {
	
	@Autowired
	private QuedadaRepositorio repo;
	
	/**
	 * Gestion las peticion HTTP GET para la url /bienvenida y /
	 * @return Muestra la vista de bienvenida (index.html).
	 */
	@GetMapping("/privada/quedadas")
	public String quedadas(Model model) {
		model.addAttribute("quedadas", repo.findAll());
		return "quedadas";
	}

}
