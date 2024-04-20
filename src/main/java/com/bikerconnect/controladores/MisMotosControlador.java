package com.bikerconnect.controladores;

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

import com.bikerconnect.dtos.MotoDTO;
import com.bikerconnect.dtos.UsuarioDTO;
import com.bikerconnect.servicios.IMotoServicio;
import com.bikerconnect.servicios.IUsuarioServicio;

import jakarta.servlet.http.HttpServletRequest;

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
        try {
            UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
            if (usuario != null) {
                model.addAttribute("misMotos", usuario.getMisMotos());
            }
            return "misMotos";
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener la lista de motocicletas");
            return "misMotos";
        }
    }

    /**
     * Gestiona la solicitud HTTP GET para la url /privada/crear-moto
     * y muestra el formulario para registrar una nueva motocicleta.
     *
     * @param model          Modelo que se utiliza para enviar un objeto MotoDTO vacío a la vista.
     * @param authentication Objeto Authentication que contiene el nombre de usuario.
     * @return La vista de registroMoto.html con el formulario para crear una nueva motocicleta.
     */
    @GetMapping("/privada/crear-moto")
    public String mostrarFormNuevaMoto(Model model, Authentication authentication) {
        try {
            UsuarioDTO usuarioSesionActual = usuarioServicio.buscarPorEmail(authentication.getName());
            MotoDTO nuevaMoto = new MotoDTO();
            nuevaMoto.setIdPropietario(usuarioSesionActual.getId());
            model.addAttribute("motoDTO", nuevaMoto);
            return "registroMoto";
        } catch (Exception e) {
            model.addAttribute("error", "Error al mostrar el formulario para crear una nueva moto");
            return "misMotos";
        }
    }

    /**
     * Procesa la solicitud HTTP POST de la url /privada/crear-moto para registro de un nueva motocicleta.
     *
     * @param motoDTO         El objeto motoDTO que recibe en el modelo y contiene los datos de la nueva moto.
     * @param model           Modelo que se utiliza para enviar mensajes y el listado actualizado a la vista.
     * @param authentication  Objeto Authentication que contiene el nombre de usuario.
     * @return La vista donde se ven las motos del usuario (misMotos.html) con mensajes de información.
     */
    @PostMapping("/privada/crear-moto")
    public String registrarMotoPost(@ModelAttribute MotoDTO motoDTO, Model model, Authentication authentication) {
        try {
            boolean motoRegistradaConExito = motoServicio.registrarMoto(motoDTO);
            UsuarioDTO usuarioSesionActual = usuarioServicio.buscarPorEmail(authentication.getName());

            if (motoRegistradaConExito) {
                model.addAttribute("altaMotoExito", "Alta de la moto en el sistema OK");
                model.addAttribute("misMotos", usuarioSesionActual.getMisMotos());
                return "misMotos";
            } else {
                model.addAttribute("altaMotoError", "No se pudo dar de alta la moto");
                model.addAttribute("misMotos", usuarioSesionActual.getMisMotos());
                return "misMotos";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar la solicitud");
            return "misMotos";
        }
    }
    
    /**
     * Gestiona la solicitud HTTP GET para la url /privada/eliminar-moto/{id} para
     * la eliminación de una motocicleta.
     * 
     * @param id El ID de la motocicleta a eliminar.
     * @param model modelos que se utiliza para enviar el listado de motocicletas y mensajes
     * @param authentication Objeto Authentication que contiene datos sobre el usuario de la sesión.
     * @return La vista de misMotos.html con el listado de motocicletas actualizado
     */
    @GetMapping("/privada/eliminar-moto/{id}")
    public String eliminarMoto(@PathVariable Long id, Model model, Authentication authentication) {
    	try {
    		MotoDTO moto = motoServicio.buscarPorId(id);
    		if (moto != null) {
				motoServicio.eliminarMoto(id);
        	    UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
                model.addAttribute("misMotos", usuario.getMisMotos());
				model.addAttribute("eliminacionCorrecta", "La moto se ha eliminado correctamente");
                return "misMotos";                
    		}
    		return "misMotos";
    		
    	} catch (Exception e) {
            model.addAttribute("Error", "Ocurrió un error al eliminar la moto");
            return "dashboard";
        }
    	
    }
    
	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/editar-moto/{id} y
	 * muestra el formulario de edición de la moto con el ID proporcionado.
	 *
	 * @param id    ID de la moto a editar.
	 * @param model Modelo que se utiliza para enviar el dto a editar a la
	 *              vista.
	 * @return La vista de editarMoto con el formulario de edición.
	 */
	@GetMapping("/privada/editar-moto/{id}")
	public String mostrarFormularioEdicion(@PathVariable Long id, Model model, HttpServletRequest request) {
		try {			
			MotoDTO motoDTO = motoServicio.buscarPorId(id);
			if (motoDTO == null) {
				return "misMotos";
			}
			model.addAttribute("motoDTO", motoDTO);
			return "editarMoto";
			
		} catch (Exception e) {
			model.addAttribute("Error", "Ocurrió un error al obtener la moto para editar");
			return "dashboard";
		}
	}
	
	/**
	 * Gestiona la solicitud HTTP POST para la url /privada/procesar-editar-moto y
	 * procesa el formulario de edición de la moto.
	 *
	 * @param motoDTO MotoDTO con los datos editados.
	 * @param model      Modelo que se utiliza para enviar mensajes y el listado
	 *                   actualizado a la vista.
	 * @return La vista de misMotos con el resultado de la
	 *         edición.
	 */
	@PostMapping("/privada/procesar-editar-moto")
	public String procesarFormularioEdicion(@ModelAttribute("motoDTO") MotoDTO motoDTO , Model model, Authentication authentication) {
		try {
			boolean motoEditadaConExito = motoServicio.actualizarMoto(motoDTO);
			UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
            model.addAttribute("misMotos", usuario.getMisMotos());
			if (motoEditadaConExito) {
				model.addAttribute("updateMotoExito", "Moto editada en el sistema con exito");
				return "misMotos";
			} else {
				model.addAttribute("updateMotoError", "No se pudo editar la moto");
				return "misMotos";
			}
		} catch (Exception e) {
			model.addAttribute("error", "Ocurrió un error al procesar la solicitud de edición de la moto"); 
			return "misMotos";
		}
		
	}
    
    

}
