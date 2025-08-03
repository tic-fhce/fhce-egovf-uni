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
@Table(name="hoja_ruta")
public class hojaRutaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable = false)
    private Long id;
    
    @Column(name="_01cite")
    private String cite;
    
    @Column(name="_02id_nota")
    private Long id_nota;
    
    @Column(name="_03fecha_creacion")
    private String fecha_creacion;
    
    @Column(name="_04estado")
    private String estado;
    
    @Column(name="_05gestion")
    private int gestion;
}