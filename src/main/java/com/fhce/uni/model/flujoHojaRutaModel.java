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
@Table(name="flujo_hoja_ruta")
public class flujoHojaRutaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable = false)
    private Long id;
    
    @Column(name="_01id_hoja_ruta")
    private Long id_hoja_ruta;
    
    @Column(name="_02orden_aprobacion")
    private int orden_aprobacion;
    
    @Column(name="_03id_unidad")
    private Long id_unidad;
    
    @Column(name="_04id_usuario")
    private Long id_usuario;
    
    @Column(name="_05estado")
    private String estado;
    
    @Column(name="_06fecha_recibido")
    private String fechaRecibido;
    
}