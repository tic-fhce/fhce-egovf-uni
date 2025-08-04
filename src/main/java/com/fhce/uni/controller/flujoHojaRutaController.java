package com.fhce.uni.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.uni.dao.flujoHojaRutaDao;
import com.fhce.uni.dto.flujoEstadoDto;
import com.fhce.uni.dto.flujoHojaRutaDtoRequest;
import com.fhce.uni.dto.flujoHojaRutaDtoResponse;
import com.fhce.uni.model.flujoHojaRutaModel;
import com.fhce.uni.service.flujoHojaRutaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/flujo-hoja-ruta")
@RequiredArgsConstructor
public class flujoHojaRutaController {
    
    private final flujoHojaRutaService flujoHojaRutaService;
    private final flujoHojaRutaDao flujoHojaRutaDao;
    private final ModelMapper modelMapper;
    
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
    @GetMapping("/hoja/{id_hoja_ruta}")
    public ResponseEntity<List<flujoHojaRutaDtoResponse>> getFlujosPorHojaRuta(@PathVariable Long id_hoja_ruta) {
        try {
            List<flujoHojaRutaModel> flujos = flujoHojaRutaDao.findByHojaRutaId(id_hoja_ruta);
            return new ResponseEntity<>(
                flujos.stream()
                    .map(flujo -> modelMapper.map(flujo, flujoHojaRutaDtoResponse.class))
                    .collect(Collectors.toList()), 
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pendientes/{id_usuario}")
    public ResponseEntity<List<flujoHojaRutaDtoResponse>> getFlujosPendientesPorUsuario(@PathVariable Long id_usuario) {
        try {
            List<flujoHojaRutaModel> flujos = flujoHojaRutaDao.findPendientesPorUsuario(id_usuario);
            return new ResponseEntity<>(
                flujos.stream()
                    .map(flujo -> modelMapper.map(flujo, flujoHojaRutaDtoResponse.class))
                    .collect(Collectors.toList()), 
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<flujoHojaRutaDtoResponse> cambiarEstadoFlujo(
            @PathVariable Long id,
            @RequestBody flujoEstadoDto estadoDto) {
        try {
            Optional<flujoHojaRutaModel> flujoOpt = flujoHojaRutaDao.findById(id);
            if (flujoOpt.isPresent()) {
                flujoHojaRutaModel flujo = flujoOpt.get();
                flujo.setEstado(estadoDto.getNuevo_estado());
                flujoHojaRutaDao.save(flujo);
                return new ResponseEntity<>(modelMapper.map(flujo, flujoHojaRutaDtoResponse.class), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/fecha")
    public ResponseEntity<flujoHojaRutaDtoResponse> registrarFechaRecepcion(
            @PathVariable Long id) {
        try {
            Optional<flujoHojaRutaModel> flujoOpt = flujoHojaRutaDao.findById(id);
            if (flujoOpt.isPresent()) {
                flujoHojaRutaModel flujo = flujoOpt.get();
                flujo.setFechaRecibido(LocalDateTime.now().toString());
                flujoHojaRutaDao.save(flujo);
                return new ResponseEntity<>(modelMapper.map(flujo, flujoHojaRutaDtoResponse.class), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}