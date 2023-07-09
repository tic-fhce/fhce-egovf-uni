package com.fhce.unidad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.unidad.dao.perteneceDao;
import com.fhce.unidad.model.perteneceModel;

@RestController
@RequestMapping("fhce-egovf-uni/pertenece")
//@RequestMapping("pertenece")
//@CrossOrigin("http://172.16.114.144:8081/")
@CrossOrigin("http://192.168.31.45:8081/")
public class perteneceController {
	
	@Autowired
	private perteneceDao perteneceDao;
	
	@GetMapping("/listar")
	public List<perteneceModel> listar(){
		return this.perteneceDao.findAll();
	}
	
	@PostMapping("/agregar")
	public void agregar(@RequestBody perteneceModel perteneceModel) {
		perteneceModel perteneceModelAux=this.perteneceDao.findPertenece(perteneceModel.get_02cif(), true);
		if (perteneceModelAux != null) {
			perteneceModelAux.set_04estado(false);
			this.perteneceDao.save(perteneceModelAux);
		}
		this.perteneceDao.save(perteneceModel);
	}

}
