package com.fhce.uni.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "API Sistema de fhce-egovf-uni",
        description = "Documentación oficial de las APIs para el sistema de fhce-egovf-uni",
        version = "1.0",
        contact = @Contact(
            name = "fhce-egovf-uni",
            email = "utic@fhce.edu.bo"
        )
    )
)
public class SwaggerConfig {
    
}