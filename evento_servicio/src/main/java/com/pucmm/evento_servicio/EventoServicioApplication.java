package com.pucmm.evento_servicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class EventoServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventoServicioApplication.class, args);
	}

}
