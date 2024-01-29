package com.bikerconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //Activa la programación de tareas programadas para comprobar las quedadas
public class BikerConnectJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikerConnectJavaApplication.class, args);
	}

}
