package com.fhce.uni.dto;

import lombok.Data;

@Data
public class citeDtoRequest {
    private String codigo_completo;
    private String prefijo;
    private String siglaUnidad;
    private int numeroSecuencial;
    private int gestion;
    private Long idUnidad;
    private Long idUsuario;
    private boolean estado;
}