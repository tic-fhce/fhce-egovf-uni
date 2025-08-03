package com.fhce.uni.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class anexoDtoResponse {
    private Long id;
    private Long id_nota;
    private String tipo;
    private String descripcion;
    private String ruta_archivo;
    private int paginas;
    private String fecha_anexo;
    private Long id_usuario_subio;
}