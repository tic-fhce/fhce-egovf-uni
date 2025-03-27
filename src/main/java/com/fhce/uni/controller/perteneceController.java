package com.fhce.uni.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.uni.dto.perteneceDtoRequest;
import com.fhce.uni.dto.perteneceDtoResponse;
import com.fhce.uni.service.perteneceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/pertenece")
@RequiredArgsConstructor
public class perteneceController {
	private final perteneceService perteneceService;
	
	@GetMapping ("/listar")
	public ResponseEntity<List<perteneceDtoResponse>>listar(){
		try {
			return new ResponseEntity<>(this.perteneceService.listar(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping ("/addPertenece")
	public ResponseEntity<perteneceDtoResponse>addPertenece(@RequestBody perteneceDtoRequest perteneceDtorequest){
		try {
			return new ResponseEntity<>(this.perteneceService.addPertenece(perteneceDtorequest),HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
