package com.fhce.uni.dto;

import lombok.Data;

@Data
public class flujoEstadoDto {
    private Long id_flujo;
    private Long id_usuario;
    private String nuevo_estado;
}