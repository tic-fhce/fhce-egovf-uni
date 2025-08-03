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
@Table(name="formato")
public class formatoModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable = false)
    private Long id;
    
    @Column(name="_01cite", unique=true)
    private String cite;
    
    @Column(name="_02id_unidad_origen")
    private Long id_unidad_origen;
    
    @Column(name="_03id_unidad_destino")
    private Long id_unidad_destino;
    
    @Column(name="_04id_usuario_creador")
    private Long id_usuario_creador;
    
    @Column(name="_05fecha_creacion")
    private String fecha_creacion;
    
    @Column(name="_06referencia")
    private String referencia;
    
    @Column(name="_07contenido")
    private String contenido;
    
    @Column(name="_08estado")
    private String estado;
    
    @Column(name="_09gestion")
    private int gestion;
    
    @Column(name="_10id_tipo_nota")
    private Long id_tipo_nota;
}
