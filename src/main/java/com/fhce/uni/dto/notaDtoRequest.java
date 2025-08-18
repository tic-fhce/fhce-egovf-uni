package com.fhce.uni.dto;

import lombok.Data;

@Data
public class notaDtoRequest {
    private Long id_unidad_destino;  
    private Long id_usuario_creador;
    private String referencia;
    private String contenido;
    private Long id_tipo_nota;
}