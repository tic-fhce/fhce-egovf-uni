package com.fhce.uni.dto;

import lombok.Data;

@Data
public class hojaRutaDtoRequest {
    private String cite;
    private Long id_nota;
    private String fecha_creacion;
    private String estado;
    private int gestion;
}