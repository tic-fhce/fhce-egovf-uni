package com.fhce.uni.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.uni.dto.notificacionDtoRequest;
import com.fhce.uni.dto.notificacionDtoResponse;
import com.fhce.uni.service.notificacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/notificacion")
@RequiredArgsConstructor
public class notificacionController {
    
    private final notificacionService notificacionService;
    
    @GetMapping("/getNotificaciones")
    public ResponseEntity<List<notificacionDtoResponse>> getNotificaciones() {
        try {
            return new ResponseEntity<>(this.notificacionService.getNotificaciones(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addNotificacion")
    public ResponseEntity<notificacionDtoResponse> addNotificacion(@RequestBody notificacionDtoRequest notificacionDtoRequest) {
        try {
            return new ResponseEntity<>(this.notificacionService.addNotificacion(notificacionDtoRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}