package com.fhce.uni.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class hojaRutaDtoResponse {
    private Long id;
    private String cite;
    private Long id_nota;
    private String fecha_creacion;
    private String estado;
    private int gestion;
}