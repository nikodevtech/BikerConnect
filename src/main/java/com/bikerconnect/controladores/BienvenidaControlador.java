package com.bikerconnect.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Clase que ejerce de controlador de la vista de bienvenida.
 */
@Controller
public class BienvenidaControlador {

	/**
	 * Gestion las peticion HTTP GET para la url /bienvenida y /
	 * @return Muestra la vista de bienvenida (index.html).
	 */
    @GetMapping({"/","/bienvenida"})
    public String bienvenida() {
        return "index";
    }
}
