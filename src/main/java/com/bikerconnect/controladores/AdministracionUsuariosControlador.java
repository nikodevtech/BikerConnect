package com.bikerconnect.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bikerconnect.dtos.UsuarioDTO;
import com.bikerconnect.entidades.Usuario;
import com.bikerconnect.servicios.IUsuarioServicio;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Clase que ejerce de controlador de la vista de administracion de usuarios para gestionar 
 * el CRUD de usuarios.
 */
@Controller
public class AdministracionUsuariosControlador {
	
	@Autowired
	private IUsuarioServicio usuarioServicio;
	
	@GetMapping("/privada/administracion-usuarios")
	public String listadoUsuarios(Model model, HttpServletRequest request,Authentication authentication) {
		List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
		model.addAttribute("usuarios", usuarios);
		if(request.isUserInRole("ROLE_ADMIN")) {
			return "administracionUsuarios";	
		} 
		model.addAttribute("noAdmin", "No eres admin");
		model.addAttribute("nombreUsuario", authentication.getName());
		return "dashboard";
	}
	
	@GetMapping("/privada/eliminar-usuario/{id}")
	public String eliminarUsuario(@PathVariable Long id, Model model, HttpServletRequest request) {
		Usuario usuario = usuarioServicio.buscarPorId(id);
		List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
		if(request.isUserInRole("ROLE_ADMIN") && usuario.getRol().equals("ROLE_ADMIN")) {
			model.addAttribute("noSePuedeEliminar", "No se puede eliminar a un admin");
			model.addAttribute("usuarios", usuarios);
			return "administracionUsuarios";
		}
		usuarioServicio.eliminar(id);
		return "redirect:/privada/administracion-usuarios";
		
	}

}
