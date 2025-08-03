package com.fhce.uni.dto;

import lombok.Data;

@Data
public class tipoNotaDtoRequest {
    private String nombreTipo;
    private String fuente;
    private Integer tamanoFuente;
    private Double margenSuperior;
    private Double margenInferior;
    private Double margenIzquierdo;
    private Double margenDerecho;
    private Double espaciadoLineal;
}