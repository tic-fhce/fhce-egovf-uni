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
@Table(name="usuario")
public class usuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable = false)
    private Long id;
    
    @Column(name="_01nombre")
    private String nombre;
    
    @Column(name="_02password")
    private String password;
    
    @Column(name="_03cif", unique=true)
    private int cif;
    
    @Column(name="_04email")
    private String email;
    
    @Column(name="_05telefono")
    private String telefono;
    
    @Column(name="_06activo")
    private boolean activo;
}