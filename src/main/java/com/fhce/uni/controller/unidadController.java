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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.uni.dao.unidadDao;
import com.fhce.uni.dto.unidadDtoRequest;
import com.fhce.uni.dto.unidadDtoResponse;
import com.fhce.uni.model.unidadModel;
import com.fhce.uni.service.unidadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/unidad")
@RequiredArgsConstructor
public class unidadController {
	
	private final unidadService unidadService;
	private final unidadDao unidadDao;
    private final ModelMapper modelMapper;
	
	@GetMapping ("/getUnidades")
	public ResponseEntity<List<unidadDtoResponse>>getUnidades(){
		try {
			return new ResponseEntity<>(this.unidadService.getUnidades(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/addUnidad")
	public ResponseEntity<unidadDtoResponse>addUnidad(@RequestBody unidadDtoRequest unidadDtoRequest){
		try {
			return new ResponseEntity<>(this.unidadService.addUnidad(unidadDtoRequest),HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/sigla/{sigla}")
    public ResponseEntity<unidadDtoResponse> getPorSigla(@PathVariable String sigla) {
        try {
            Optional<unidadModel> unidadOpt = unidadDao.findBySigla(sigla);
            return unidadOpt.map(unidad -> new ResponseEntity<>(
                modelMapper.map(unidad, unidadDtoResponse.class),
                HttpStatus.OK
            )).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/jerarquia")
    public ResponseEntity<List<unidadDtoResponse>> getJerarquiaCompleta() {
        try {
            List<unidadModel> unidadesRaiz = unidadDao.findByDependienteIsNull();
            List<unidadDtoResponse> jerarquia = unidadesRaiz.stream()
                .map(u -> modelMapper.map(u, unidadDtoResponse.class))
                .collect(Collectors.toList());
            
            return new ResponseEntity<>(jerarquia, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dependientes/{id_unidad}")
    public ResponseEntity<List<unidadDtoResponse>> getDependientes(@PathVariable Long id_unidad) {
        try {
            Optional<unidadModel> unidadOpt = unidadDao.findById(id_unidad);
            if (unidadOpt.isPresent()) {
                String nombreUnidad = unidadOpt.get().getUnidad();
                List<unidadModel> dependientes = unidadDao.findByDependiente(nombreUnidad);
                return new ResponseEntity<>(
                    dependientes.stream()
                        .map(u -> modelMapper.map(u, unidadDtoResponse.class))
                        .collect(Collectors.toList()),
                    HttpStatus.OK
                );
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
