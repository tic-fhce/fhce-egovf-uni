package com.fhce.uni.dto;

import lombok.Data;

@Data
public class notificacionDtoRequest {
    private Long id_usuario;
    private Long id_flujo_aprobacion;
    private String tipo;
    private String mensaje;
    private String fecha_hora;
    private boolean leido;
    private String accion_url;
}