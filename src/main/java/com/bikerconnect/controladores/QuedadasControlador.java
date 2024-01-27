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

import com.bikerconnect.dtos.QuedadaDTO;
import com.bikerconnect.dtos.UsuarioDTO;
import com.bikerconnect.entidades.Quedada;
import com.bikerconnect.repositorios.QuedadaRepositorio;
import com.bikerconnect.servicios.IQuedadaServicio;
import com.bikerconnect.servicios.IUsuarioToDto;

/**
 * Clase que ejerce de controlador de la vista de quedadas
 */
@Controller
public class QuedadasControlador {

	@Autowired
	private IQuedadaServicio quedadaServicio;
	
	@Autowired
	private QuedadaRepositorio quedadaRepo;
	
	@Autowired
	private IUsuarioToDto toDto;
	

	/**
	 * Gestiona las peticion HTTP GET para la url /privada/quedadas
	 * 
	 * @param model El modelo en el que se guarda lista quedadas DTO para la vista
	 * @return Muestra la vista de quedadas.
	 */
	@GetMapping("/privada/quedadas")
	public String quedadas(Model model) {
		try {
			model.addAttribute("quedadas", quedadaServicio.obtenerQuedadas());
			return "quedadas";
		} catch (Exception e) {
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
			return "quedadas";
		}

	}

	/**
	 * Gestiona las peticion HTTP GET para la url
	 * /privada/quedadas/planificar-quedada
	 * 
	 * @param model El modelo en el que se guardan los datos para la vista
	 * @return Muestra la vista para planificar quedada (registroQuedada.html).
	 */
	@GetMapping("/privada/quedadas/planificar-quedada")
	public String planificarQuedada(Model model) {
		model.addAttribute("quedadaDTO", new QuedadaDTO());
		return "registroQuedada";
	}

	/**
	 * Procesa la peticion HTTP POST para la url
	 * /privada/quedadas/planificar-quedada
	 * 
	 * @param quedada La quedada a planificar
	 * @param model   El modelo en el que se guardan los datos desde la vista
	 * @return Muestra la vista de quedadas (quedadas.html)
	 */
	@PostMapping("/privada/quedadas/planificar-quedada")
	public String registrarQuedada(@ModelAttribute("quedadaDTO") QuedadaDTO quedada, Model model) {
		try {
			boolean quedadaCreada = quedadaServicio.crearQuedada(quedada);
			if (quedadaCreada) {
				model.addAttribute("quedadas", quedadaServicio.obtenerQuedadas());
				model.addAttribute("quedadaCreadaExito", "Quedada planificada correctamente");
			} else {
				model.addAttribute("quedadas", quedadaServicio.obtenerQuedadas());
				model.addAttribute("quedadaCreadaError", "No se pudo registrar la quedada");
			}
			return "quedadas";
		} catch (Exception e) {
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
			return "quedadas";
		}

	}

	/**
	 * Gestiona la peticion HTTP GET para la url /privada/quedadas/detalle-quedada/{id}
	 * muestra la vista de detalle de la quedada 
	 * 
	 * @param id id de la quedada a mostrar
	 * @param model el modelo en el que se guardan la quedadaDTO
	 * @return la vista de detalle de la quedada
	 */
	@GetMapping("/privada/quedadas/detalle-quedada/{id}")
	public String verDetallesQuedada(@PathVariable Long id, Model model) {

		QuedadaDTO quedada = quedadaServicio.obtenerQuedadaPorId(id);
		if (quedada != null) {
			Quedada quedadaDao = quedadaRepo.findById(id).get();
			List<UsuarioDTO> participantes = toDto.listaUsuarioToDto(quedadaDao.getUsuariosParticipantes());
			model.addAttribute("quedada", quedada);
			model.addAttribute("participantes", participantes);
			return "detalleQuedada"; 
		} else {
			return "redirect:/privada/quedadas"; 
		}

	}
	
	@GetMapping("/privada/quedadas/detalle-quedada/unirse/{id}")
	public String unirseQuedada(@PathVariable Long id, Model model, Authentication auth) {
		
		boolean unidoCorrectamente = quedadaServicio.unirseQuedada(id, auth.getName());

		if (unidoCorrectamente) {
			Quedada quedada = quedadaRepo.findById(id).get();
			List<UsuarioDTO> participantes = toDto.listaUsuarioToDto(quedada.getUsuariosParticipantes());
			model.addAttribute("quedadas", quedadaServicio.obtenerQuedadas());
			model.addAttribute("participantes", participantes);
			model.addAttribute("quedadaAsistenciaExito", "Se ha unido correctamente");
			return "quedadas";
		} else {
			model.addAttribute("quedadaAsistenciaError", "No se ha podido unir a la quedada");
			return "quedadas";
		}
	}

}
