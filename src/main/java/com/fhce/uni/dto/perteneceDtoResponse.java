package com.fhce.uni.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class perteneceDtoResponse {
    private Long id;
    private Long id_unidad;
    private Long id_usuario;
    private String fecha;
    private boolean estado;
    private int gestion;
    private String rol;
}