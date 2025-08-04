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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.uni.dao.hojaRutaDao;
import com.fhce.uni.dto.hojaRutaDtoRequest;
import com.fhce.uni.dto.hojaRutaDtoResponse;
import com.fhce.uni.model.hojaRutaModel;
import com.fhce.uni.service.hojaRutaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/hoja-ruta")
@RequiredArgsConstructor
public class hojaRutaController {
    
    private final hojaRutaService hojaRutaService;
    private final hojaRutaDao hojaRutaDao;
    private final ModelMapper modelMapper;
    
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
    @GetMapping("/nota/{id_nota}")
    public ResponseEntity<hojaRutaDtoResponse> getHojaRutaPorNota(@PathVariable Long id_nota) {
        try {
            Optional<hojaRutaModel> hojaOpt = hojaRutaDao.findByNotaId(id_nota);
            return hojaOpt.map(hoja -> new ResponseEntity<>(
                modelMapper.map(hoja, hojaRutaDtoResponse.class), 
                HttpStatus.OK
            )).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cite/{cite}")
    public ResponseEntity<hojaRutaDtoResponse> getHojaRutaPorCite(@PathVariable String cite) {
        try {
            Optional<hojaRutaModel> hojaOpt = hojaRutaDao.findByCite(cite);
            return hojaOpt.map(hoja -> new ResponseEntity<>(
                modelMapper.map(hoja, hojaRutaDtoResponse.class), 
                HttpStatus.OK
            )).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<hojaRutaDtoResponse> cambiarEstadoHojaRuta(
            @PathVariable Long id,
            @RequestParam String estado) {
        try {
            Optional<hojaRutaModel> hojaOpt = hojaRutaDao.findById(id);
            if (hojaOpt.isPresent()) {
                hojaRutaModel hoja = hojaOpt.get();
                hoja.setEstado(estado);
                hojaRutaDao.save(hoja);
                return new ResponseEntity<>(modelMapper.map(hoja, hojaRutaDtoResponse.class), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}