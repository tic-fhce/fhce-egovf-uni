package com.fhce.uni.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class flujoHojaRutaDtoResponse {
    private Long id;
    private Long id_nota;
    private int orden_aprobacion;
    private Long id_unidad_revisora;
    private String rol_requerido;
    private Long id_usuario_revisor;
    private String estado;
    private String fecha_asignacion;
    private String fecha_revision;
    private String observaciones;
}