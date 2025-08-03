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
@Table(name="anexo")
public class anexoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable = false)
    private Long id;
    
    @Column(name="_01id_nota")
    private Long id_nota;
    
    @Column(name="_02tipo")
    private String tipo;
    
    @Column(name="_03descripcion")
    private String descripcion;
    
    @Column(name="_04ruta_archivo")
    private String ruta_archivo;
    
    @Column(name="_05paginas")
    private int paginas;
    
    @Column(name="_06fecha_anexo")
    private String fecha_anexo;
    
    @Column(name="_07id_usuario_subio")
    private Long id_usuario_subio;
}