package com.fhce.uni.dto;

import lombok.Data;

@Data
public class perteneceDtoResponse {
	private Long id;
	private Long id_unidad;
	private Long cif;
	private String fecha;
	private boolean estado;

}
