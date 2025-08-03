package com.fhce.uni.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.uni.dto.usuarioDtoRequest;
import com.fhce.uni.dto.usuarioDtoResponse;
import com.fhce.uni.service.usuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/usuario")
@RequiredArgsConstructor
public class usuarioController {
    
    private final usuarioService usuarioService;
    
    @GetMapping("/getUsuarios")
    public ResponseEntity<List<usuarioDtoResponse>> getUsuarios() {
        try {
            return new ResponseEntity<>(this.usuarioService.getUsuarios(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addUsuario")
    public ResponseEntity<usuarioDtoResponse> addUsuario(@RequestBody usuarioDtoRequest usuarioDtoRequest) {
        try {
            return new ResponseEntity<>(this.usuarioService.addUsuario(usuarioDtoRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}