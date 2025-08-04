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
import org.springframework.web.bind.annotation.RestController;

import com.fhce.uni.dao.notificacionDao;
import com.fhce.uni.dto.notificacionDtoRequest;
import com.fhce.uni.dto.notificacionDtoResponse;
import com.fhce.uni.model.notificacionModel;
import com.fhce.uni.service.notificacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/notificacion")
@RequiredArgsConstructor
public class notificacionController {
    
    private final notificacionService notificacionService;
    private final notificacionDao notificacionDao;
    private final ModelMapper modelMapper;
    
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
    @GetMapping("/usuario/{id_usuario}")
    public ResponseEntity<List<notificacionDtoResponse>> getNotificacionesPorUsuario(@PathVariable Long id_usuario) {
        try {
            List<notificacionModel> notificaciones = notificacionDao.findByUsuarioId(id_usuario);
            return new ResponseEntity<>(
                notificaciones.stream()
                    .map(notif -> modelMapper.map(notif, notificacionDtoResponse.class))
                    .collect(Collectors.toList()),
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/leido")
    public ResponseEntity<notificacionDtoResponse> marcarComoLeida(@PathVariable Long id) {
        try {
            Optional<notificacionModel> notifOpt = notificacionDao.findById(id);
            if (notifOpt.isPresent()) {
                notificacionModel notificacion = notifOpt.get();
                notificacion.setLeido(true);
                notificacionDao.save(notificacion);
                return new ResponseEntity<>(modelMapper.map(notificacion, notificacionDtoResponse.class), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/no-leidas/{id_usuario}")
    public ResponseEntity<List<notificacionDtoResponse>> getNotificacionesNoLeidas(@PathVariable Long id_usuario) {
        try {
            List<notificacionModel> notificaciones = notificacionDao.findNoLeidasPorUsuario(id_usuario);
            return new ResponseEntity<>(
                notificaciones.stream()
                    .map(notif -> modelMapper.map(notif, notificacionDtoResponse.class))
                    .collect(Collectors.toList()),
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}