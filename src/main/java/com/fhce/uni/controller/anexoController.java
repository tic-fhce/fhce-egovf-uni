package com.fhce.uni.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fhce.uni.dao.anexoDao;
import com.fhce.uni.dto.anexoDtoRequest;
import com.fhce.uni.dto.anexoDtoResponse;
import com.fhce.uni.model.anexoModel;
import com.fhce.uni.service.anexoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/anexo")
@RequiredArgsConstructor
public class anexoController {
    
    private final anexoService anexoService;
    private final anexoDao anexoDao;
    private final ModelMapper modelMapper; // Add this line
    
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
    
    @GetMapping("/nota/{id_nota}")
    public ResponseEntity<List<anexoDtoResponse>> getAnexosPorNota(@PathVariable Long id_nota) {
        try {
            List<anexoModel> anexos = anexoDao.findByNotaId(id_nota);
            return new ResponseEntity<>(
                anexos.stream()
                    .map(anexo -> modelMapper.map(anexo, anexoDtoResponse.class))
                    .collect(Collectors.toList()), 
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuario/{id_usuario}")
    public ResponseEntity<List<anexoDtoResponse>> getAnexosPorUsuario(@PathVariable Long id_usuario) {
        try {
            List<anexoModel> anexos = anexoDao.findByUsuarioId(id_usuario);
            return new ResponseEntity<>(
                anexos.stream()
                    .map(anexo -> modelMapper.map(anexo, anexoDtoResponse.class))
                    .collect(Collectors.toList()), 
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<anexoDtoResponse> uploadAnexo(
            @RequestParam("archivo") MultipartFile archivo,
            @RequestParam("id_nota") Long id_nota,
            @RequestParam("id_usuario") Long id_usuario) {
        try {
            String rutaArchivo = "ruta/del/archivo/" + archivo.getOriginalFilename();
            
            anexoDtoRequest request = new anexoDtoRequest();
            request.setId_nota(id_nota);
            request.setTipo(archivo.getContentType());
            request.setDescripcion(archivo.getOriginalFilename());
            request.setRuta_archivo(rutaArchivo);
            request.setPaginas(0);
            request.setFecha_anexo(LocalDateTime.now().toString());
            request.setId_usuario_subio(id_usuario);

            return new ResponseEntity<>(anexoService.addAnexo(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}