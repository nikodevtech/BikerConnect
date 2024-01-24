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

@Controller
public class MisMotosControlador {


	@Autowired
	private IMotoServicio motoServicio;
	
	@Autowired
	private IUsuarioServicio usuarioServicio;

	@GetMapping("/privada/mis-motos")
	public String mostrarMisMotos(Authentication authentication, Model model) {
		UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
		if (usuario != null) {
			List<MotoDTO> motos = motoServicio.obtenerMotosPorIdUsuario(usuario.getId());
			model.addAttribute("misMotos", motos);
		}
		return "misMotos";
	}


}
