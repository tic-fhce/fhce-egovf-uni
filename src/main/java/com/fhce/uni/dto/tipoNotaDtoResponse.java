package com.fhce.uni.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tipoNotaDtoResponse {
    private Long id;
    private String nombreTipo;
    private String fuente;
    private Integer tamanoFuente;
    private Double margenSuperior;
    private Double margenInferior;
    private Double margenIzquierdo;
    private Double margenDerecho;
    private Double espaciadoLineal;
}