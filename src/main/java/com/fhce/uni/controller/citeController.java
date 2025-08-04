package com.fhce.uni.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.uni.dao.citeDao;
import com.fhce.uni.dto.citeDtoRequest;
import com.fhce.uni.dto.citeDtoResponse;
import com.fhce.uni.model.citeModel;
import com.fhce.uni.service.citeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/cite")
@RequiredArgsConstructor
public class citeController {
    
    private final citeService citeService;
    private final citeDao citeDao;
    private final ModelMapper modelMapper;
    
    @GetMapping("/getCites")
    public ResponseEntity<List<citeDtoResponse>> getCites() {
        try {
            return new ResponseEntity<>(this.citeService.getCites(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addCite")
    public ResponseEntity<citeDtoResponse> addCite(@RequestBody citeDtoRequest citeDtoRequest) {
        try {
            return new ResponseEntity<>(this.citeService.addCite(citeDtoRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/ultimo/{prefijo}/{gestion}")
    public ResponseEntity<Integer> getUltimoNumeroCite(
            @PathVariable String prefijo,
            @PathVariable int gestion) {
        try {
            Integer ultimoNumero = citeDao.findUltimoNumeroCite(prefijo, gestion);
            return new ResponseEntity<>(ultimoNumero != null ? ultimoNumero : 0, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/unidad/{id_unidad}")
    public ResponseEntity<List<citeDtoResponse>> getCitesPorUnidad(@PathVariable Long id_unidad) {
        try {
            List<citeModel> cites = citeDao.findByIdUnidad(id_unidad);
            return new ResponseEntity<>(
                cites.stream()
                    .map(cite -> modelMapper.map(cite, citeDtoResponse.class))
                    .collect(Collectors.toList()), 
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<citeDtoResponse> cambiarEstadoCite(
            @PathVariable Long id,
            @RequestParam boolean estado) {
        try {
            Optional<citeModel> citeOpt = citeDao.findById(id);
            if (citeOpt.isPresent()) {
                citeModel cite = citeOpt.get();
                cite.setEstado(estado);
                citeDao.save(cite);
                return new ResponseEntity<>(modelMapper.map(cite, citeDtoResponse.class), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}