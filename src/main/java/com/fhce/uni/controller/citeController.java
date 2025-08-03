package com.fhce.uni.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.uni.dto.citeDtoRequest;
import com.fhce.uni.dto.citeDtoResponse;
import com.fhce.uni.service.citeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-uni/cite")
@RequiredArgsConstructor
public class citeController {
    
    private final citeService citeService;
    
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
}