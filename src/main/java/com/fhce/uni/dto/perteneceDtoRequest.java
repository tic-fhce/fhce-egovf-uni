package com.fhce.uni.dto;

import lombok.Data;

@Data
public class perteneceDtoRequest {
	private Long id_unidad;
	private Long cif;
	private String fecha;
	private boolean estado;
	private int gestion;

}
