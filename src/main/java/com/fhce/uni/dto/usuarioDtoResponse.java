package com.fhce.uni.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class usuarioDtoResponse {
    private Long id;
    private String nombre;
    private int cif;
    private String email;
    private String telefono;
    private boolean activo;
}