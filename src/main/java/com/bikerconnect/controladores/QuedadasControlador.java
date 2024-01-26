package com.bikerconnect.controladores;

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
import com.bikerconnect.entidades.Usuario;
import com.bikerconnect.repositorios.QuedadaRepositorio;
import com.bikerconnect.repositorios.UsuarioRepositorio;
import com.bikerconnect.servicios.IQuedadaServicio;
import com.bikerconnect.servicios.IQuedadaToDao;
import com.bikerconnect.servicios.IUsuarioServicio;
import com.bikerconnect.servicios.IUsuarioToDao;

/**
 * Clase que ejerce de controlador de la vista de quedadas
 */
@Controller
public class QuedadasControlador {

	@Autowired
	private IQuedadaServicio quedadaServicio;
	
	@Autowired
	private IUsuarioServicio usuarioServicio;
	
	@Autowired
	private QuedadaRepositorio quedadaRepo;
	
	@Autowired
	private UsuarioRepositorio usuarioRepo;

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
			model.addAttribute("quedada", quedada);
			return "detalleQuedada"; 
		} else {
			return "redirect:/privada/quedadas"; 
		}

	}
	
	@GetMapping("/privada/quedadas/detalle-quedada/unirse/{id}")
	public String unirseQuedada(@PathVariable Long id, Model model, Authentication auth) {
				
		Quedada q = quedadaRepo.findById(id).get();
		Usuario u = usuarioRepo.findFirstByEmail(auth.getName());
			
		u.getQuedadasParticipante().add(q);
		q.getUsuariosParticipantes().add(u);
		
		usuarioRepo.save(u);
		quedadaRepo.save(q);

		model.addAttribute("quedadas", quedadaServicio.obtenerQuedadas());
		return "quedadas";
	}

}
