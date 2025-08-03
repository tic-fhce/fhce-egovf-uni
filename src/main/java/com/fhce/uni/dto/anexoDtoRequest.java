package com.fhce.uni.dto;

import lombok.Data;

@Data
public class anexoDtoRequest {
    private Long id_nota;
    private String tipo;
    private String descripcion;
    private String ruta_archivo;
    private int paginas;
    private String fecha_anexo;
    private Long id_usuario_subio;
}