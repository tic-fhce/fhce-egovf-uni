package com.fhce.uni.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "API Sistema de Ecommerce FHCE",
        description = "Documentación oficial de las APIs para el sistema de Ecommerce",
        version = "1.0",
        contact = @Contact(
            name = "Tu Nombre o Equipo",
            email = "tucorreo@fhce.edu.uy"
        )
    )
)
public class SwaggerConfig {
    
}