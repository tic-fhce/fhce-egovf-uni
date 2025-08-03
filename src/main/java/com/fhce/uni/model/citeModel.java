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
@Table(name="cite")
public class citeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable = false)
    private Long id;
    
    @Column(name="_01codigo_completo", unique=true)
    private String codigo_completo;
    
    @Column(name="_02prefijo")
    private String prefijo;
    
    @Column(name="_03sigla_unidad")
    private String siglaUnidad;
    
    @Column(name="_05numero_secuencial")
    private int numeroSecuencial;
    
    @Column(name="_06gestion")
    private int gestion;
    
    @Column(name="_07id_unidad")
    private Long idUnidad;
    
    @Column(name="_08id_usuario")
    private Long idUsuario;
    
    @Column(name="_09estado")
    private boolean estado;
    
}