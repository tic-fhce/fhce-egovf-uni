package com.fhce.unidad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnidadApplication /*extends SpringBootServletInitializer*/{

	/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(UnidadApplication.class);
	} para produccion*/

	public static void main(String[] args) {
		SpringApplication.run(UnidadApplication.class, args);
	}

}
