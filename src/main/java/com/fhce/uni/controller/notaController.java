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

import com.fhce.uni.dao.notaDao;
import com.fhce.uni.dto.notaDtoRequest;
import com.fhce.uni.dto.notaDtoResponse;
import com.fhce.uni.model.notaModel;
import com.fhce.uni.service.notaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/nota")
@RequiredArgsConstructor
public class notaController {
    
    private final notaService notaService;
    private final notaDao notaDao;
    private final ModelMapper modelMapper;
    
    @GetMapping("/getNotas")
    public ResponseEntity<List<notaDtoResponse>> getNotas() {
        try {
            return new ResponseEntity<>(this.notaService.getNotas(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addNota")
    public ResponseEntity<notaDtoResponse> addNota(@RequestBody notaDtoRequest notaDtoRequest) {
        try {
            return new ResponseEntity<>(this.notaService.addNota(notaDtoRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/usuario/{id_usuario}")
    public ResponseEntity<List<notaDtoResponse>> getNotasPorUsuario(@PathVariable Long id_usuario) {
        try {
            List<notaModel> notas = notaDao.findByUsuarioCreador(id_usuario);
            return new ResponseEntity<>(
                notas.stream()
                    .map(nota -> modelMapper.map(nota, notaDtoResponse.class))
                    .collect(Collectors.toList()), 
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/unidad-origen/{id_unidad}")
    public ResponseEntity<List<notaDtoResponse>> getNotasPorUnidadOrigen(@PathVariable Long id_unidad) {
        try {
            List<notaModel> notas = notaDao.findByUnidadOrigen(id_unidad);
            return new ResponseEntity<>(
                notas.stream()
                    .map(nota -> modelMapper.map(nota, notaDtoResponse.class))
                    .collect(Collectors.toList()), 
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/unidad-destino/{id_unidad}")
    public ResponseEntity<List<notaDtoResponse>> getNotasPorUnidadDestino(@PathVariable Long id_unidad) {
        try {
            List<notaModel> notas = notaDao.findByUnidadDestino(id_unidad);
            return new ResponseEntity<>(
                notas.stream()
                    .map(nota -> modelMapper.map(nota, notaDtoResponse.class))
                    .collect(Collectors.toList()), 
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cite/{cite}")
    public ResponseEntity<notaDtoResponse> getNotaPorCite(@PathVariable String cite) {
        try {
            Optional<notaModel> notaOpt = notaDao.findByCite(cite);
            return notaOpt.map(nota -> new ResponseEntity<>(
                modelMapper.map(nota, notaDtoResponse.class), 
                HttpStatus.OK
            )).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<notaDtoResponse> cambiarEstadoNota(
            @PathVariable Long id,
            @RequestParam String estado) {
        try {
            Optional<notaModel> notaOpt = notaDao.findById(id);
            if (notaOpt.isPresent()) {
                notaModel nota = notaOpt.get();
                nota.setEstado(estado);
                notaDao.save(nota);
                return new ResponseEntity<>(modelMapper.map(nota, notaDtoResponse.class), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/generar-pdf/{id}")
    public ResponseEntity<String> generarPdfNota(@PathVariable Long id) {
        try {
            Optional<notaModel> notaOpt = notaDao.findById(id);
            if (notaOpt.isPresent()) {
                String rutaPdf = "generated/nota_" + id + ".pdf";
                return new ResponseEntity<>(rutaPdf, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}