package com.fhce.uni.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.uni.dao.hojaRutaDao;
import com.fhce.uni.dto.hojaRutaDtoRequest;
import com.fhce.uni.dto.hojaRutaDtoResponse;
import com.fhce.uni.model.hojaRutaModel;
import com.fhce.uni.service.hojaRutaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class hojaRutaServiceImpl implements hojaRutaService {
    
    private final hojaRutaDao hojaRutaDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public List<hojaRutaDtoResponse> getHojasRuta() {
        return this.hojaRutaDao.findAll().stream()
                .map(hoja -> this.modelMapper.map(hoja, hojaRutaDtoResponse.class))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public hojaRutaDtoResponse addHojaRuta(hojaRutaDtoRequest hojaRutaDtoRequest) {
        hojaRutaModel hojaRutaModel = this.modelMapper.map(hojaRutaDtoRequest, hojaRutaModel.class);
        this.hojaRutaDao.save(hojaRutaModel);
        return this.modelMapper.map(hojaRutaModel, hojaRutaDtoResponse.class);
    }
}