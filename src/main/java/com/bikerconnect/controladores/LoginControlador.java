package com.bikerconnect.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginControlador {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
