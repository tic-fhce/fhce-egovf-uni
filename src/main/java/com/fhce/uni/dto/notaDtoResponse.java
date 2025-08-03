package com.fhce.uni.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class notaDtoResponse {
    private Long id;
    private String cite;
    private Long id_unidad_origen;
    private Long id_unidad_destino;
    private Long id_usuario_creador;
    private String fecha_creacion;
    private String referencia;
    private String contenido;
    private String estado;
    private int gestion;
    private Long id_tipo_nota;
}