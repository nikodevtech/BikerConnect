package com.bikerconnect.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bikerconnect.dtos.UsuarioDTO;

@Controller
public class RegistroControlador {

	/**
	 * Gestiona la solicitud HTTP GET para la url /auth/registrar y mostrar la página de registro.
	 * @param model Modelo que se utiliza para enviar un usuarioDTO vacio a la vista.
	 * @return La vista de registro de usuario (registro.html).
	 */
	@GetMapping("/auth/crear-cuenta")
	public String registrarGet(Model model) {
		model.addAttribute("usuarioDTO", new UsuarioDTO());
		return "registro";
	}
	

}
