package com.fhce.unidad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class UnidadApplication /*extends SpringBootServletInitializer*/{

	/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(UnidadApplication.class);
	}*//* para produccion*/

	public static void main(String[] args) {
		SpringApplication.run(UnidadApplication.class, args);
	}

}
