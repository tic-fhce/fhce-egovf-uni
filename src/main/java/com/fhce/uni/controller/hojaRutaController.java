package com.fhce.uni.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.uni.dto.hojaRutaDtoRequest;
import com.fhce.uni.dto.hojaRutaDtoResponse;
import com.fhce.uni.service.hojaRutaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/hoja-ruta")
@RequiredArgsConstructor
public class hojaRutaController {
    
    private final hojaRutaService hojaRutaService;
    
    @GetMapping("/getHojasRuta")
    public ResponseEntity<List<hojaRutaDtoResponse>> getHojasRuta() {
        try {
            return new ResponseEntity<>(this.hojaRutaService.getHojasRuta(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addHojaRuta")
    public ResponseEntity<hojaRutaDtoResponse> addHojaRuta(@RequestBody hojaRutaDtoRequest hojaRutaDtoRequest) {
        try {
            return new ResponseEntity<>(this.hojaRutaService.addHojaRuta(hojaRutaDtoRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}