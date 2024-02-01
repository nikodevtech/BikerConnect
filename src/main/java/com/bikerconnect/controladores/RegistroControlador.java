package com.bikerconnect.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bikerconnect.dtos.UsuarioDTO;
import com.bikerconnect.servicios.IUsuarioServicio;

/**
 * Clase que ejerce de controlador de la vista de registro para gestionar las
 * solicitudes relacionadas con la creación de nuevas cuentas de usuario.
 */
@Controller
public class RegistroControlador {

    @Autowired
    private IUsuarioServicio usuarioServicio;

    /**
     * Gestiona la solicitud HTTP GET para la url /auth/registrar y muestra la página de registro.
     *
     * @param model Modelo que se utiliza para enviar un usuarioDTO vacío a la vista.
     * @return La vista de registro de usuario (registro.html).
     */
    @GetMapping("/auth/crear-cuenta")
    public String registrarGet(Model model) {
        try {
            model.addAttribute("usuarioDTO", new UsuarioDTO());
            return "registro";
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
            return "registro";
        }
    }

    /**
     * Procesa la solicitud HTTP POST para registro de un nuevo usuario.
     *
     * @param usuarioDTO El objeto UsuarioDTO que recibe en el modelo y contiene los
     *                   datos del nuevo usuario.
     * @param model      Modelo que se utiliza para enviar mensajes a la vista.
     * @return La vista de inicio de sesión (login.html) si fue exitoso el registro;
     * de lo contrario, la vista de registro de usuario (registro.html).
     */
    @PostMapping("/auth/crear-cuenta")
    public String registrarPost(@ModelAttribute UsuarioDTO usuarioDTO, Model model) {
        try {
            UsuarioDTO nuevoUsuario = usuarioServicio.registrarUsuario(usuarioDTO);

            if (nuevoUsuario == null) {
				model.addAttribute("usuarioRegistradoPeroNoConfirmado", "Ya existe un usuario con ese email sin confirmar");
				return "registro";
			} else if(nuevoUsuario.getMensajeError().equals("Usuario ya registrado y confirmado")) {
                // De lo contrario, es que ya existe un usuario con el dicho email
                model.addAttribute("emailYaRegistrado", "Ya existe un usuario con ese email");
                return "registro";
            } else if (nuevoUsuario != null && !nuevoUsuario.isCuentaConfirmada()) {
                // Si entra a este if es que el registro se completó correctamente
                model.addAttribute("mensajeRegistroExitoso", "Registro del nuevo usuario OK");
                return "login";
            } else if (nuevoUsuario.isCuentaConfirmada()) {
                model.addAttribute("mensajeRegistroExitoso", "Registro del nuevo usuario OK");
                model.addAttribute("usuarios", usuarioServicio.obtenerTodos());
                return "administracionUsuarios";
            } 
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
            return "registro";
        }
        return "registro";
    }

}
