package com.fhce.unidad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pertenece")
public class perteneceModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private Long _01id_unidad;
	
	@Column
	private Long _02cif;
	
	@Column
	private String _03fecha;
	
	@Column
	private boolean _04estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long get_01id_unidad() {
		return _01id_unidad;
	}

	public void set_01id_unidad(Long _01id_unidad) {
		this._01id_unidad = _01id_unidad;
	}

	public Long get_02cif() {
		return _02cif;
	}

	public void set_02cif(Long _02cif) {
		this._02cif = _02cif;
	}

	public String get_03fecha() {
		return _03fecha;
	}

	public void set_03fecha(String _03fecha) {
		this._03fecha = _03fecha;
	}

	public boolean is_04estado() {
		return _04estado;
	}

	public void set_04estado(boolean _04estado) {
		this._04estado = _04estado;
	}
	
}
