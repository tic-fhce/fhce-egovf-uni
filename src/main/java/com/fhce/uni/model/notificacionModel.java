package com.fhce.uni.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="notificacion")
public class notificacionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable = false)
    private Long id;
    
    @Column(name="_01id_usuario")
    private Long id_usuario;
    
    @Column(name="_02id_flujo_hoja_ruta")
    private Long id_flujo_hoja_ruta;
    
    @Column(name="_03tipo")
    private String tipo;
    
    @Column(name="_04mensaje")
    private String mensaje;
    
    @Column(name="_05fecha_hora")
    private String fecha_hora;
    
    @Column(name="_06leido")
    private boolean leido;
    
    @Column(name="_07accion_url")
    private String accion_url;
}