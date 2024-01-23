package com.bikerconnect.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bikerconnect.dtos.UsuarioDTO;
import com.bikerconnect.servicios.IUsuarioServicio;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Clase que ejerce de controlador de la vista de administracion de usuarios
 * para gestionar el CRUD de usuarios.
 */
@Controller
public class AdministracionUsuariosControlador {

	@Autowired
	private IUsuarioServicio usuarioServicio;

	@GetMapping("/privada/administracion-usuarios")
	public String listadoUsuarios(Model model, HttpServletRequest request, Authentication authentication) {
		List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
		model.addAttribute("usuarios", usuarios);
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "administracionUsuarios";
		}
		model.addAttribute("noAdmin", "No eres admin");
		model.addAttribute("nombreUsuario", authentication.getName());
		return "dashboard";
	}

	@GetMapping("/privada/eliminar-usuario/{id}")
	public String eliminarUsuario(@PathVariable Long id, Model model, HttpServletRequest request) {
		UsuarioDTO usuario = usuarioServicio.buscarPorId(id);
		List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
		if (request.isUserInRole("ROLE_ADMIN") && usuario.getRol().equals("ROLE_ADMIN")) {
			model.addAttribute("noSePuedeEliminar", "No se puede eliminar a un admin");
			model.addAttribute("usuarios", usuarios);
			return "administracionUsuarios";
		}
		usuarioServicio.eliminar(id);
		model.addAttribute("eliminacionCorrecta", "El usuario se ha eliminado correctamente");
		return "administracionUsuarios";

	}

	@GetMapping("/privada/editar-usuario/{id}")
	public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
		UsuarioDTO usuarioDTO = usuarioServicio.buscarPorId(id);
		if (usuarioDTO == null) {
			return "administracionUsuarios";
		}
		model.addAttribute("usuarioDTO", usuarioDTO);
		return "editarUsuario";
	}

	@PostMapping("/privada/procesar-editar")
	public String procesarFormularioEdicion(@ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO, Model model) {
		try {
			usuarioServicio.actualizarUsuario(usuarioDTO);
			model.addAttribute("edicionCorrecta", "El Usuario se ha editado correctamente");
			return "administracionUsuarios";
		} catch (Exception e) {
			model.addAttribute("error", "Error al editar el usuario. Por favor, inténtalo de nuevo.");
			return "editar";
		}
	}

}
