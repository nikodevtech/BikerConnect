package com.bikerconnect.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bikerconnect.dtos.MotoDTO;
import com.bikerconnect.dtos.UsuarioDTO;
import com.bikerconnect.servicios.IMotoServicio;
import com.bikerconnect.servicios.IUsuarioServicio;

/**
 * Clase que ejerce de controlador de la vista de mis motos
 * para gestionar las solicitudes relacionadas con las motocicletas del usuario.
 */
@Controller
public class MisMotosControlador {


	@Autowired
	private IMotoServicio motoServicio;
	
	@Autowired
	private IUsuarioServicio usuarioServicio;

    /**
     * Gestiona la solicitud HTTP GET para la url /privada/mis-motos
     * y muestra la página con la lista de motocicletas del usuario autenticado.
     *
     * @param authentication Objeto Authentication que contiene el nombre de usuario.
     * @param model          Modelo que se utiliza para enviar la lista de motocicletas a la vista.
     * @return La vista de misMotos.html con la lista de motocicletas del usuario.
     */
	@GetMapping("/privada/mis-motos")
	public String mostrarMisMotos(Authentication authentication, Model model) {
		UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
		if (usuario != null) {
			List<MotoDTO> motos = motoServicio.obtenerMotosPorIdUsuario(usuario.getId());
			model.addAttribute("misMotos", motos);
		}
		return "misMotos";
	}
	
    /**
     * Gestiona la solicitud HTTP GET para la url /privada/crear-moto
     * y muestra el formulario para registrar una nueva motocicleta.
     *
     * @param model Modelo que se utiliza para enviar un objeto MotoDTO vacío a la vista.
     * @return La vista de registroMoto.html con el formulario para crear una nueva motocicleta.
     */
	@GetMapping("/privada/crear-moto")
	public String mostrarFormNuevaMoto(Model model) {
		model.addAttribute("motoDTO", new MotoDTO());
		return "registroMoto";
	}
	


}
