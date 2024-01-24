package com.bikerconnect.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bikerconnect.servicios.IUsuarioServicio;


/**
 * Clase que ejerce de controlador de la vista de login para gestionar las
 * solicitudes relacionadas con la autenticación.
 */
@Controller
public class LoginControlador {

	@Autowired
    private IUsuarioServicio usuarioServicio;

	/**
	 * Gestiona la solicitud HTTP GET para la url /auth/login y muestra la página de inicio de sesión
	 * @param model Modelo que se utiliza para enviar un usuarioDTO vacio a la vista.
	 * @return La vista de inicio de sesión (login.html).
	 */
	@GetMapping("/auth/iniciar-sesion")
	public String login() {
		return "login";
	}
	
	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/cuenta-usuario y muestra 
	 * la página de cuenta de usuario si la cuenta fue confirmada, de lo contrario, 
	 * la vista de inicio de sesión.
	 * @param model modelos que se utiliza para enviar mensajes a la vista.
	 * @param authentication Objeto Authentication que contiene el nombre de usuario
	 * @return La vista de cuenta de usuario (cuentaDeUsuario.html) si la cuenta fue confirmada 
	 * 		   o la vista de inicio de sesión (login.html).
	 */
	@GetMapping("/privada/dashboard")
	public String loginCorrecto(Model model, Authentication authentication) {

	    boolean cuentaConfirmada = usuarioServicio.estaLaCuentaConfirmada(authentication.getName());    

	    if (cuentaConfirmada) {
	        model.addAttribute("nombreUsuario", authentication.getName());
	        return "dashboard";
	    } else {
	    	 model.addAttribute("cuentaNoVerificada", "Error al confirmar su email");
	        return "login";
	    }
	}
	
	/**
     * Gestiona la solicitud HTTP GET para la url /auth/confirmar-cuenta y realiza 
     * la confirmación de la cuenta.
     *
     * @param model Modelo que se utiliza para enviar mensajes a la vista.
     * @param token Token de confirmación enviado al usuario.
     * @return La vista de confirmación de cuenta.
     */
    @GetMapping("/auth/confirmar-cuenta")
    public String confirmarCuenta(Model model, @RequestParam("token") String token) {
    	
        boolean confirmacionExitosa = usuarioServicio.confirmarCuenta(token);

        if (confirmacionExitosa) {
            model.addAttribute("cuentaVerificada", "Su dirección de correo ha sido confirmada correctamente");
        } else {
            model.addAttribute("cuentaNoVerificada", "Error al confirmar su email");
        }

        return "login";
    }

}
