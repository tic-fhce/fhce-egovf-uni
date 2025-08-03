package com.fhce.uni.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class citeDtoResponse {
    private Long id;
    private String codigo_completo;
    private String prefijo;
    private String siglaUnidad;
    private int numeroSecuencial;
    private int gestion;
    private Long idUnidad;
    private Long idUsuario;
    private boolean estado;
}