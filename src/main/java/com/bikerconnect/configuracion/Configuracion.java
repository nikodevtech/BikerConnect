package com.bikerconnect.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bikerconnect.servicios.ConvertidorStringToCalendar;

/**
 * Clase para otras configuraciones de spring
 */
@Configuration
public class Configuracion implements WebMvcConfigurer {
	
    /**
     * Se le informa a Spring la clase ConvertidorStringToCalendar que se
     * encargara de convertir String a Calendar.
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ConvertidorStringToCalendar());
    }

}
