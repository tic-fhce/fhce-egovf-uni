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

import com.fhce.uni.dao.usuarioDao;
import com.fhce.uni.dto.usuarioDtoRequest;
import com.fhce.uni.dto.usuarioDtoResponse;
import com.fhce.uni.model.usuarioModel;
import com.fhce.uni.service.usuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/usuario")
@RequiredArgsConstructor
public class usuarioController {
    
    private final usuarioService usuarioService;
    private final usuarioDao usuarioDao;
    private final ModelMapper modelMapper;
    
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
    @GetMapping("/cif/{cif}")
    public ResponseEntity<usuarioDtoResponse> getPorCif(@PathVariable int cif) {
        try {
            Optional<usuarioModel> usuarioOpt = usuarioDao.findByCif(cif);
            return usuarioOpt.map(usuario -> new ResponseEntity<>(
                modelMapper.map(usuario, usuarioDtoResponse.class),
                HttpStatus.OK
            )).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<usuarioDtoResponse> getPorEmail(@PathVariable String email) {
        try {
            Optional<usuarioModel> usuarioOpt = usuarioDao.findByEmail(email);
            return usuarioOpt.map(usuario -> new ResponseEntity<>(
                modelMapper.map(usuario, usuarioDtoResponse.class),
                HttpStatus.OK
            )).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/contrasena")
    public ResponseEntity<usuarioDtoResponse> cambiarContrasena(
            @PathVariable Long id,
            @RequestParam String nuevaContrasena) {
        try {
            Optional<usuarioModel> usuarioOpt = usuarioDao.findById(id);
            if (usuarioOpt.isPresent()) {
                usuarioModel usuario = usuarioOpt.get();
                usuario.setPassword(nuevaContrasena); // Debe estar encriptada
                usuarioDao.save(usuario);
                return new ResponseEntity<>(modelMapper.map(usuario, usuarioDtoResponse.class), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/activar")
    public ResponseEntity<usuarioDtoResponse> cambiarEstado(
            @PathVariable Long id,
            @RequestParam boolean activo) {
        try {
            Optional<usuarioModel> usuarioOpt = usuarioDao.findById(id);
            if (usuarioOpt.isPresent()) {
                usuarioModel usuario = usuarioOpt.get();
                usuario.setActivo(activo);
                usuarioDao.save(usuario);
                return new ResponseEntity<>(modelMapper.map(usuario, usuarioDtoResponse.class), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<usuarioDtoResponse> login(
            @RequestParam int cif,
            @RequestParam String password) {
        try {
            Optional<usuarioModel> usuarioOpt = usuarioDao.findByCif(cif);
            if (usuarioOpt.isPresent()) {
                usuarioModel usuario = usuarioOpt.get();
                // Validar contraseña (comparar hash)
                if (usuario.getPassword().equals(password)) { // Debe usar BCrypt
                    return new ResponseEntity<>(modelMapper.map(usuario, usuarioDtoResponse.class), HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}