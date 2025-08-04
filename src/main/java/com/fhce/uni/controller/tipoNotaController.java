package com.fhce.uni.controller;

import java.util.List;
import java.util.Optional;

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

import com.fhce.uni.dao.tipoNotaDao;
import com.fhce.uni.dto.tipoNotaDtoRequest;
import com.fhce.uni.dto.tipoNotaDtoResponse;
import com.fhce.uni.model.tipoNotaModel;
import com.fhce.uni.service.tipoNotaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/tipo-nota")
@RequiredArgsConstructor
public class tipoNotaController {
    
    private final tipoNotaService tipoNotaService;
    private final tipoNotaDao tipoNotaDao;
    private final ModelMapper modelMapper;
    
    @GetMapping("/getTiposNota")
    public ResponseEntity<List<tipoNotaDtoResponse>> getTiposNota() {
        try {
            return new ResponseEntity<>(this.tipoNotaService.getTiposNota(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addTipoNota")
    public ResponseEntity<tipoNotaDtoResponse> addTipoNota(@RequestBody tipoNotaDtoRequest tipoNotaDtoRequest) {
        try {
            return new ResponseEntity<>(this.tipoNotaService.addTipoNota(tipoNotaDtoRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<tipoNotaDtoResponse> getPorNombre(@PathVariable String nombre) {
        try {
            Optional<tipoNotaModel> tipoOpt = tipoNotaDao.findByNombreTipo(nombre);
            return tipoOpt.map(tipo -> new ResponseEntity<>(
                modelMapper.map(tipo, tipoNotaDtoResponse.class),
                HttpStatus.OK
            )).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/formato")
    public ResponseEntity<tipoNotaDtoResponse> actualizarFormato(
            @PathVariable Long id,
            @RequestBody tipoNotaDtoRequest formatoRequest) {
        try {
            Optional<tipoNotaModel> tipoOpt = tipoNotaDao.findById(id);
            if (tipoOpt.isPresent()) {
                tipoNotaModel tipo = tipoOpt.get();
                tipo.setFuente(formatoRequest.getFuente());
                tipo.setTamanoFuente(formatoRequest.getTamanoFuente());
                tipo.setMargenSuperior(formatoRequest.getMargenSuperior());
                tipo.setMargenInferior(formatoRequest.getMargenInferior());
                tipo.setMargenIzquierdo(formatoRequest.getMargenIzquierdo());
                tipo.setMargenDerecho(formatoRequest.getMargenDerecho());
                tipo.setEspaciadoLineal(formatoRequest.getEspaciadoLineal());
                
                tipoNotaDao.save(tipo);
                return new ResponseEntity<>(modelMapper.map(tipo, tipoNotaDtoResponse.class), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}