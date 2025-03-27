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
@Table(name="pertenece")
public class perteneceModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column (name="_01id_unidad")
	private Long id_unidad;
	
	@Column (name="_02cif")
	private Long cif;
	
	@Column (name="_03fecha")
	private String fecha;
	
	@Column (name="_04estado")
	private boolean estado;
	
	@Column (name="_05gestion")
	private int gestion;

}
