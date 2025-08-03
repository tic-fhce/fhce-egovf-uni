package com.fhce.uni.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.uni.dao.anexoDao;
import com.fhce.uni.dto.anexoDtoRequest;
import com.fhce.uni.dto.anexoDtoResponse;
import com.fhce.uni.model.anexoModel;
import com.fhce.uni.service.anexoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class anexoServiceImpl implements anexoService {
    
    private final anexoDao anexoDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public List<anexoDtoResponse> getAnexos() {
        return this.anexoDao.findAll().stream()
                .map(anexo -> this.modelMapper.map(anexo, anexoDtoResponse.class))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public anexoDtoResponse addAnexo(anexoDtoRequest anexoDtoRequest) {
        anexoModel anexoModel = this.modelMapper.map(anexoDtoRequest, anexoModel.class);
        this.anexoDao.save(anexoModel);
        return this.modelMapper.map(anexoModel, anexoDtoResponse.class);
    }
}