package com.fhce.uni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FhceEgovfUniApplication /*extends SpringBootServletInitializer*/{
	/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FhceEgovfUniApplication.class);
	}*//* para produccion*/

	public static void main(String[] args) {
		SpringApplication.run(FhceEgovfUniApplication.class, args);
	}

}
