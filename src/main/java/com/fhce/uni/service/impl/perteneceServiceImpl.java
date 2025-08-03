package com.fhce.uni.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.uni.dao.perteneceDao;
import com.fhce.uni.dto.perteneceDtoRequest;
import com.fhce.uni.dto.perteneceDtoResponse;
import com.fhce.uni.model.perteneceModel;
import com.fhce.uni.service.perteneceService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class perteneceServiceImpl implements perteneceService {
    
    private final perteneceDao perteneceDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public List<perteneceDtoResponse> getPerteneces() {
        return this.perteneceDao.findAll().stream()
                .map(pertenece -> this.modelMapper.map(pertenece, perteneceDtoResponse.class))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public perteneceDtoResponse addPertenece(perteneceDtoRequest perteneceDtoRequest) {
        perteneceModel perteneceModel = this.modelMapper.map(perteneceDtoRequest, perteneceModel.class);
        perteneceModel.setEstado(true); // Por defecto activo al crear
        this.perteneceDao.save(perteneceModel);
        return this.modelMapper.map(perteneceModel, perteneceDtoResponse.class);
    }
}