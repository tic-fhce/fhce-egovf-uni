package com.fhce.uni.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.uni.dao.tipoNotaDao;
import com.fhce.uni.dto.tipoNotaDtoRequest;
import com.fhce.uni.dto.tipoNotaDtoResponse;
import com.fhce.uni.model.tipoNotaModel;
import com.fhce.uni.service.tipoNotaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class tipoNotaServiceImpl implements tipoNotaService {
    
    private final tipoNotaDao tipoNotaDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public List<tipoNotaDtoResponse> getTiposNota() {
        return this.tipoNotaDao.findAll().stream()
                .map(tipo -> this.modelMapper.map(tipo, tipoNotaDtoResponse.class))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public tipoNotaDtoResponse addTipoNota(tipoNotaDtoRequest tipoNotaDtoRequest) {
        tipoNotaModel tipoNotaModel = this.modelMapper.map(tipoNotaDtoRequest, tipoNotaModel.class);
        this.tipoNotaDao.save(tipoNotaModel);
        return this.modelMapper.map(tipoNotaModel, tipoNotaDtoResponse.class);
    }
}