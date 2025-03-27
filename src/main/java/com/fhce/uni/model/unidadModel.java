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
@Table(name="unidad")
public class unidadModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column (name="_01unidad")
	private String unidad;
	
	@Column (name = "_02dependiente")
	private String dependiente;
	
	@Column (name = "_03sigla")
	private String sigla;
	
	@Column (name = "_04telefono")
	private String telefono;
	
	@Column (name = "_05correo")
	private String correo;
}
