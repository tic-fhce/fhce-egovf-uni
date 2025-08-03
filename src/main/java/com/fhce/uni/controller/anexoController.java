package com.fhce.uni.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.uni.dto.anexoDtoRequest;
import com.fhce.uni.dto.anexoDtoResponse;
import com.fhce.uni.service.anexoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/anexo")
@RequiredArgsConstructor
public class anexoController {
    
    private final anexoService anexoService;
    
    @GetMapping("/getAnexos")
    public ResponseEntity<List<anexoDtoResponse>> getAnexos() {
        try {
            return new ResponseEntity<>(this.anexoService.getAnexos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addAnexo")
    public ResponseEntity<anexoDtoResponse> addAnexo(@RequestBody anexoDtoRequest anexoDtoRequest) {
        try {
            return new ResponseEntity<>(this.anexoService.addAnexo(anexoDtoRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}