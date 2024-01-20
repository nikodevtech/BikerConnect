package com.bikerconnect.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bikerconnect.dtos.UsuarioDTO;
import com.bikerconnect.servicios.IUsuarioServicio;


@Controller
public class SolicitarRecuperacionControlador {

	@Autowired
	private IUsuarioServicio usuarioServicio;
	
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
	
	/**
	 * Procesa la solicitud HTTP POST para la url /auth/iniciarRecuperacion 
	 * Se utiliza el email del usuario para intentar iniciar el proceso de recuperación.
	 * @param usuarioDTO El objeto UsuarioDTO que recibe del modelo y contiene el email del usuario.
	 * @param model El modelo que se utiliza para enviar mensajes a la vista.
	 * @return La vista de login.html si el envío del email fue exitoso; 
	 * 		   en caso contrario, la vista de inicio de recuperación.html
	 */
	@PostMapping("/auth/iniciar-recuperacion")
	public String procesarInicioRecuperacion(@ModelAttribute UsuarioDTO usuarioDTO, Model model) {
		System.out.println(usuarioDTO);
		boolean envioConExito = usuarioServicio.iniciarProcesoRecuperacion(usuarioDTO.getEmailUsuario());
		
		if(envioConExito) {
	        model.addAttribute("mensajeExitoMail", "Proceso de recuperacion OK");
	        return "login";
		} else {
	        model.addAttribute("mensajeErrorMail", "Error en el proceso de recuperacion.");
		}
		return "solicitarRecuperacionPassword";
	}
}
