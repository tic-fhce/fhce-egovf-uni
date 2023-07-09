package com.fhce.unidad.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.unidad.model.perteneceModel;

public interface perteneceDao extends JpaRepository <perteneceModel, Long>{
	
	@Query(value = "select * from pertenece where _02cif=? and _04estado = ?",nativeQuery=true)
	perteneceModel findPertenece(Long cif,boolean estado);

}
