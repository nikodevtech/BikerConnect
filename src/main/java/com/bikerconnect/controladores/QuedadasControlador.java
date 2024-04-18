package com.bikerconnect.controladores;

import java.util.Calendar;
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
import com.bikerconnect.servicios.IUsuarioServicio;
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
	
	@Autowired
	private IUsuarioServicio usuarioServicio;
	

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
	 * Gestiona las peticion HTTP GET para la url /privada/quedadas/planificar-quedada
	 * mostrando la vista de planificar quedada con un objeto QuedadaDTO vacio
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
	 * Procesa la peticion HTTP POST para la url /privada/quedadas/planificar-quedada
	 * registra una nueva quedada en la base de datos con los datos del DTO
	 * 
	 * @param quedada La quedada a planificar
	 * @param model   El modelo en el que se guardan los datos desde la vista
	 * @param auth    Objeto Authentication que contiene el nombre de usuario
	 * @return Muestra la vista de quedadas (quedadas.html)
	 */
	@PostMapping("/privada/quedadas/planificar-quedada")
	public String registrarQuedada(@ModelAttribute("quedadaDTO") QuedadaDTO quedada, Model model, Authentication authentication) {
		try {
			quedada.setUsuarioOrganizador(authentication.getName());
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
	 * muestra la vista de detalle de la quedada por el id de la URL
	 * 
	 * @param id id de la quedada a mostrar
	 * @param model el modelo en el que se guarda los datos
	 * @return la vista de detalle de la quedada
	 */
	@GetMapping("/privada/quedadas/detalle-quedada/{id}")
	public String verDetallesQuedada(@PathVariable Long id, Model model) {

		try {	
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
		} catch (Exception e) {
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
			return "quedadas";
		}

	}
	
	/**
	 * Gestiona la peticion HTTP GET para la url /privada/quedadas/detalle-quedada/unirse/{id}
	 * con el procedimiento de unirse a la quedada por parte del usuario autenticado
	 * 
	 * @param id id de la quedada
	 * @param model el modelo en el que se guardan los datos de participantes, la quedada y los mensajes al usuario
	 * @param auth representa al usuario autenticado
	 * @return la vista de las quedadas
	 */
	@GetMapping("/privada/quedadas/detalle-quedada/unirse/{id}")
	public String unirseQuedada(@PathVariable Long id, Model model, Authentication auth) {
	    try {
	    	String mensaje = quedadaServicio.unirseQuedada(id, auth.getName());
	    	List<QuedadaDTO> quedadas = quedadaServicio.obtenerQuedadas();
	    	model.addAttribute("quedadas", quedadas);
	    	switch (mensaje) {
		    	case "Usuario unido a la quedada":
		            model.addAttribute("quedadaAsistenciaExito", "Se ha unido correctamente");
		    		break;
		    	case "Ya estás unido a esta quedada":
		    		model.addAttribute("quedadaAsistenciaInfo", "Ya estás unido a esta quedada");
		    		break;
		    	case "La quedada está completada":
		    		model.addAttribute("quedadaYaCompletada", "La quedada está completada");
		    		break;
		    	case "La quedada ya ha pasado":
		    		model.addAttribute("quedadaPasada", "La fecha de la quedada ya ha pasado");
		    		break;
	    	}
	        return "quedadas";
	    } catch (Exception e) {
	        model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
	        return "quedadas";
	    }
	}
	
	/**
	 * Gestiona la peticion HTTP GET para la url /privada/quedadas/detalle-quedada/cancelar/{id}
	 * con el procedimiento de cancelar la asistencia a la quedada por parte del usuario autenticado
	 * 
	 * @param id id de la quedada a cancelar
	 * @param model el modelo en el que se guardan los datos de la quedada y los mensajes al usuario
	 * @param auth instancia que representa al usuario autenticado
	 * @return la vista de las quedadas
	 */
	@GetMapping("/privada/quedadas/detalle-quedada/cancelar-asistencia/{id}")
	public String cancelarAsistenciaQuedada(@PathVariable Long id, Model model, Authentication auth) {
	    try {
	    	if (!quedadaServicio.estaUsuarioUnido(id, auth.getName())) {
	                model.addAttribute("quedadaCancelacionInfo", "No estás unido a esta quedada");
	                model.addAttribute("quedadas", quedadaServicio.obtenerQuedadas());
	        } else {
	        	boolean canceladoCorrectamente = quedadaServicio.cancelarAsistenciaQuedada(id, auth.getName());

		        if (canceladoCorrectamente) {
		            model.addAttribute("quedadas", quedadaServicio.obtenerQuedadas());
		            model.addAttribute("quedadaCancelacionExito", "Se ha cancelado la asistencia correctamente");
		        } else {
		            model.addAttribute("quedadas", quedadaServicio.obtenerQuedadas());       
			        model.addAttribute("quedadaCancelacionError", "No se ha podido cancelar la asistencia");
		        }
	        }
	        
	        return "quedadas";
	    } catch (Exception e) {
	        model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
	        return "quedadas";
	    }
	}
	
	/**
	 * Gestiona la peticion HTTP GET para la url /privada/quedadas/mis-quedadas
	 * para mostrar la vista de misQuedadas con las quedadas del usuario
	 * 
	 * @param model el modelo en el que se guardan los datos de las quedadas
	 * @param auth instancia que representa al usuario autenticado
	 * @return la vista misQuedadas
	 */
	@GetMapping("/privada/quedadas/mis-quedadas")
	public String misQuedadas(Model model, Authentication auth) {
		try {
			UsuarioDTO usuario = usuarioServicio.buscarPorEmail(auth.getName());
			if (usuario != null) {
				model.addAttribute("misQuedadas", usuario.getMisQuedadas());
			}		
		} catch (Exception e) {
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, reintente.");
			return "quedadas";
		}		
		return "misQuedadas";
	}
	
	/**
	 * Gestiona la peticion HTTP GET para la url /privada/quedadas/detalle-quedada/cancelar-quedada/{id}
	 * con el procedimiento de cancelar la quedada por parte del usuario organizador.
	 * 
	 * @param id El id de la quedada a cancelar
	 * @param model El modelo en el que se guardan los datos de la quedada y los mensajes al usuario
	 * @param auth La instancia que representa al usuario autenticado
	 * @return La vista de las quedadas
	 */
	@GetMapping("/privada/quedadas/detalle-quedada/cancelar-quedada/{id}")
	public String cancelarQuedada(@PathVariable Long id, Model model, Authentication auth) {
		try {
			QuedadaDTO q = quedadaServicio.obtenerQuedadaPorId(id);
			
			if(q.getUsuarioOrganizador().equals(auth.getName())) {
				
				String mensaje = quedadaServicio.cancelarQuedada(id);
		    	List<QuedadaDTO> quedadas = quedadaServicio.obtenerQuedadas();

		    	model.addAttribute("quedadas", quedadas);
				
				switch (mensaje) {
					case "Quedada cancelada":
						model.addAttribute("quedadaCancelacionQuedadaExito", "Se ha cancelado la asistencia correctamente");
						model.addAttribute("quedadas", quedadaServicio.obtenerQuedadas());
						break;
					case "Quedada completada":
						model.addAttribute("quedadaCancelacionCompletada", "No se puede cancelar una quedada completada");
						break;
					case "Usuarios participantes":
						model.addAttribute("quedadaCancelacionParticipantes", "No se puede cancelar una quedada con participantes");
						break;
				}
				
			} else {
				model.addAttribute("quedadaCancelacionPermiso", "No tiene permiso para eliminar esta quedada");
			}			
			
		} catch (Exception e) {
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, reintente.");
			return "quedadas";
		}		
		return "quedadas";
	}
	
	/**
	 * Gestiona la peticion HTTP GET para la url /privada/quedadas/detalle-quedada/completar-quedada/{id}
	 * con el procedimiento de marcar como completada la quedada por parte del usuario organizador.
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/privada/quedadas/detalle-quedada/completar-quedada/{id}")
    public String marcarQuedadaComoCompletada(@PathVariable long id, Model model) {
        try {
            QuedadaDTO quedada = quedadaServicio.obtenerQuedadaPorId(id); 
            boolean hayParticipantes = quedadaServicio.verificarParticipantesQuedada(id);

            if (quedada != null) {
            	if(!quedada.getFechaHora().before(Calendar.getInstance())) {
					model.addAttribute("quedadaNoCompletada", "Quedada no marcada como completada");           		
                }else if (!hayParticipantes) {
					model.addAttribute("quedadaNoParticipantes", "No hay participantes para la quedada");        	
                } else {
                    quedadaServicio.actualizarQuedada(quedada.getId());
                    model.addAttribute("quedadaCompletada", "Quedada marcada como completada");
                } 

                List<QuedadaDTO> quedadas = quedadaServicio.obtenerQuedadas();
                model.addAttribute("quedadas", quedadas);

                return "quedadas";
            } else {
                return "redirect:/quedadas";
            }
        } catch (Exception ex) {
            model.addAttribute("error", "Error al procesar la solicitud. Por favor, reintente.");
            return "quedadas";
        }
    }



}
