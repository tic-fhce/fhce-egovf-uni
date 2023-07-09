package com.fhce.unidad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="unidad")
public class unidadModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private String _01unidad;
	
	@Column
	private String _02dependiente;
	
	@Column
	private String _03sigla;
	
	@Column
	private String _04telefono;
	
	@Column
	private String _05correo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String get_01unidad() {
		return _01unidad;
	}

	public void set_01unidad(String _01unidad) {
		this._01unidad = _01unidad;
	}

	public String get_02dependiente() {
		return _02dependiente;
	}

	public void set_02dependiente(String _02dependiente) {
		this._02dependiente = _02dependiente;
	}

	public String get_03sigla() {
		return _03sigla;
	}

	public void set_03sigla(String _03sigla) {
		this._03sigla = _03sigla;
	}

	public String get_04telefono() {
		return _04telefono;
	}

	public void set_04telefono(String _04telefono) {
		this._04telefono = _04telefono;
	}

	public String get_05correo() {
		return _05correo;
	}

	public void set_05correo(String _05correo) {
		this._05correo = _05correo;
	}
	
}
