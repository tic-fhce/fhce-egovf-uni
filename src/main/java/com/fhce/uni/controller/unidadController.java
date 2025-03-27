package com.fhce.uni.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.uni.dto.unidadDtoRequest;
import com.fhce.uni.dto.unidadDtoResponse;
import com.fhce.uni.service.unidadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/unidad")
@RequiredArgsConstructor
public class unidadController {
	
	private final unidadService unidadService;
	
	@GetMapping ("/getUnidades")
	public ResponseEntity<List<unidadDtoResponse>>getUnidades(){
		try {
			return new ResponseEntity<>(this.unidadService.getUnidades(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/addUnidad")
	public ResponseEntity<unidadDtoResponse>addUnidad(@RequestBody unidadDtoRequest unidadDtoRequest){
		try {
			return new ResponseEntity<>(this.unidadService.addUnidad(unidadDtoRequest),HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
