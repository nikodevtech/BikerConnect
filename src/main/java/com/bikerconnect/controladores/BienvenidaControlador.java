package com.bikerconnect.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BienvenidaControlador {

    @GetMapping({"/","/bienvenida"})
    public String bienvenida() {
        return "index";
    }
}
