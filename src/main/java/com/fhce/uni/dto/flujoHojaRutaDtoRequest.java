package com.fhce.uni.dto;

import lombok.Data;

@Data
public class flujoHojaRutaDtoRequest {
    private Long id_hoja_ruta;
    private int orden_aprobacion;
    private Long id_unidad;
    private Long id_usuario;
    private String estado;
    private String fechaRecibido;
}