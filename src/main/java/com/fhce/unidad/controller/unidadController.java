package com.fhce.unidad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.unidad.dao.unidadDao;
import com.fhce.unidad.model.unidadModel;

@RestController
@RequestMapping("fhce-egovf-uni/unidad")
//@RequestMapping("unidad")
//@CrossOrigin("http://172.16.114.144:8081/")
@CrossOrigin("http://192.168.31.45:8081/")
public class unidadController {
	
	@Autowired
	private unidadDao unidadDao;
	
	@GetMapping("/listar")
	public List<unidadModel> listar(){
		return this.unidadDao.findAll();
	}
	
	@PostMapping("/agregar")
	public void agregar(@RequestBody unidadModel unidadModel) {
		this.unidadDao.save(unidadModel);
	}

}
