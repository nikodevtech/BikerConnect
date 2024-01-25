package com.bikerconnect.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	private IUsuarioServicio usuarioServicio;
	
	@Autowired 
	private IMotoServicio motoServicio;

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
			model.addAttribute("misMotos", usuario.getMisMotos());
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
	public String mostrarFormNuevaMoto(Model model, Authentication authentication) {
		UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
		MotoDTO moto = new MotoDTO();
		moto.setIdPropietario(usuario.getId());
		model.addAttribute("motoDTO", moto);
		return "registroMoto";
	}
	
	@PostMapping("/privada/crear-moto")
	public String registrarMotoPost(@ModelAttribute MotoDTO motoDTO, Model model, Authentication authentication) {
		
		try {
			boolean exito = motoServicio.registrarMoto(motoDTO);
			UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());

			if(exito) {
				model.addAttribute("altaMotoExito", "Alta de la moto en el sistema OK");
				model.addAttribute("misMotos", usuario.getMisMotos());
				return "misMotos";
			} else {
				model.addAttribute("altaMotoError", "No se pudo dar de alta la moto");
				model.addAttribute("misMotos", usuario.getMisMotos());
				return "misMotos";
			}
		} catch(Exception e) {
			System.out.println( "[Error MisMotosControlador - registrarMotoPost()] Error al registrar nueva moto " + e.getMessage());
		}
		return "misMotos";
	
	}
	


}
