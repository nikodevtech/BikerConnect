package com.bikerconnect.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bikerconnect.dtos.UsuarioDTO;


@Controller
public class SolicitarRecuperacionControlador {

	/**
	 * Gestiona la solicitud HTTP GET para la url /auth/iniciarRecuperacion 
	 * y muestra la vista de inicio de recuperación.
	 * @param model El modelo en el que se añade como atributo un objeto usuarioDTO vacío para enviarlo a la vista.
	 * @return La vista de solicitarRecuperacionPassword.html
	 */
	@GetMapping("/auth/solicitar-recuperacion")
	public String mostrarVistainiciarRecuperacion(Model model) {
		model.addAttribute("usuarioDTO", new UsuarioDTO());
		return "solicitarRecuperacionPassword";
	}
}
