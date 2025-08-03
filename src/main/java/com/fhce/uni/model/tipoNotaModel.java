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
@Table(name="tipo_nota")
public class tipoNotaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable = false)
    private Long id;

    @Column(name="nombre_tipo", unique=true, nullable = false)
    private String nombreTipo;

    @Column(name="fuente")
    private String fuente;

    @Column(name="tamano_fuente")
    private Integer tamanoFuente;

    @Column(name="margen_superior")
    private Double margenSuperior; 

    @Column(name="margen_inferior")
    private Double margenInferior;

    @Column(name="margen_izquierdo")
    private Double margenIzquierdo;

    @Column(name="margen_derecho")
    private Double margenDerecho;

    @Column(name="espaciado_lineal")
    private Double espaciadoLineal; //  1.5, 2.0
    
}