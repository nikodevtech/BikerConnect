package com.bikerconnect.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bikerconnect.entidades.Moto;
import com.bikerconnect.repositorios.MotoRepositorio;

@Controller
public class MisMotosControlador {

	@Autowired
	private MotoRepositorio motoRepositorio;

	@GetMapping("/privada/mis-motos")
	public String mostrarMisMotos() {
		return "misMotos";
	}


}
