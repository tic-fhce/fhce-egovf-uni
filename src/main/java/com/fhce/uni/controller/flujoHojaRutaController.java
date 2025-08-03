package com.fhce.uni.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fhce.uni.dto.flujoHojaRutaDtoRequest;
import com.fhce.uni.dto.flujoHojaRutaDtoResponse;
import com.fhce.uni.service.flujoHojaRutaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/flujo-hoja-ruta")
@RequiredArgsConstructor
public class flujoHojaRutaController {
    
    private final flujoHojaRutaService flujoHojaRutaService;
    
    @GetMapping("/getFlujos")
    public ResponseEntity<List<flujoHojaRutaDtoResponse>> getFlujosHojaRuta() {
        try {
            return new ResponseEntity<>(flujoHojaRutaService.getFlujosHojaRuta(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addFlujo")
    public ResponseEntity<flujoHojaRutaDtoResponse> addFlujoHojaRuta(
            @RequestBody flujoHojaRutaDtoRequest request) {
        try {
            return new ResponseEntity<>(
                flujoHojaRutaService.addFlujoHojaRuta(request), 
                HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}