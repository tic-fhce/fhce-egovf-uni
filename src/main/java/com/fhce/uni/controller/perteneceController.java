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

import com.fhce.uni.dao.perteneceDao;
import com.fhce.uni.dto.perteneceDtoRequest;
import com.fhce.uni.dto.perteneceDtoResponse;
import com.fhce.uni.model.perteneceModel;
import com.fhce.uni.service.perteneceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/pertenece")
@RequiredArgsConstructor
public class perteneceController {
    
    private final perteneceService perteneceService;
    private final perteneceDao perteneceDao;
    private final ModelMapper modelMapper;
    
    @GetMapping("/getPerteneces")
    public ResponseEntity<List<perteneceDtoResponse>> getPerteneces() {
        try {
            return new ResponseEntity<>(this.perteneceService.getPerteneces(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addPertenece")
    public ResponseEntity<perteneceDtoResponse> addPertenece(@RequestBody perteneceDtoRequest perteneceDtoRequest) {
        try {
            return new ResponseEntity<>(this.perteneceService.addPertenece(perteneceDtoRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/activos/{id_unidad}")
    public ResponseEntity<List<perteneceDtoResponse>> getMiembrosActivos(@PathVariable Long id_unidad) {
        try {
            // CAMBIO AQUÍ: Usar el nuevo nombre del método
            List<perteneceModel> miembros = perteneceDao.findByIdUnidadAndEstado(id_unidad, true);
            return new ResponseEntity<>(
                miembros.stream()
                    .map(m -> modelMapper.map(m, perteneceDtoResponse.class))
                    .collect(Collectors.toList()),
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/secretarios/{id_unidad}")
    public ResponseEntity<List<perteneceDtoResponse>> getSecretarios(@PathVariable Long id_unidad) {
        try {
            List<perteneceModel> secretarios = perteneceDao.findByUnidadYRol(id_unidad, "SECRETARIO");
            return new ResponseEntity<>(
                secretarios.stream()
                    .map(s -> modelMapper.map(s, perteneceDtoResponse.class))
                    .collect(Collectors.toList()),
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/rol")
    public ResponseEntity<perteneceDtoResponse> cambiarRol(
            @PathVariable Long id,
            @RequestParam String nuevoRol) {
        try {
            Optional<perteneceModel> perteneceOpt = perteneceDao.findById(id);
            if (perteneceOpt.isPresent()) {
                perteneceModel pertenece = perteneceOpt.get();
                pertenece.setRol(nuevoRol);
                perteneceDao.save(pertenece);
                return new ResponseEntity<>(modelMapper.map(pertenece, perteneceDtoResponse.class), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<perteneceDtoResponse> cambiarEstado(
            @PathVariable Long id,
            @RequestParam boolean estado) {
        try {
            Optional<perteneceModel> perteneceOpt = perteneceDao.findById(id);
            if (perteneceOpt.isPresent()) {
                perteneceModel pertenece = perteneceOpt.get();
                pertenece.setEstado(estado);
                perteneceDao.save(pertenece);
                return new ResponseEntity<>(modelMapper.map(pertenece, perteneceDtoResponse.class), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}