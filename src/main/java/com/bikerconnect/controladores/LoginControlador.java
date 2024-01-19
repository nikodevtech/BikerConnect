package com.bikerconnect.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * Clase que ejerce de controlador de la vista de login/registro para gestionar las
 * solicitudes relacionadas con la autenticación y registro de usuarios.
 */
@Controller
public class LoginControlador {


	/**
	 * Gestiona la solicitud HTTP GET para la url /auth/login y muestra la página de inicio de sesión
	 * @param model Modelo que se utiliza para enviar un usuarioDTO vacio a la vista.
	 * @return La vista de inicio de sesión (login.html).
	 */
	@GetMapping("/auth/iniciar-sesion")
	public String login() {
		return "login";
	}
	
	@GetMapping("/privada/cuenta-usuario")
	public String loginCorrecto(Model model, Authentication authentication) {
		model.addAttribute("credencialesCorrectas", "Credenciales correctas, bienvenido");
		model.addAttribute("nombreUsuario", authentication.getName());
		return "cuentaDeUsuario";
	}

}
