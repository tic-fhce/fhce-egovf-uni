package com.fhce.uni.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.uni.dao.citeDao;
import com.fhce.uni.dto.citeDtoRequest;
import com.fhce.uni.dto.citeDtoResponse;
import com.fhce.uni.model.citeModel;
import com.fhce.uni.service.citeService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class citeServiceImpl implements citeService {
    
    private final citeDao citeDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public List<citeDtoResponse> getCites() {
        return this.citeDao.findAll().stream()
                .map(cite -> this.modelMapper.map(cite, citeDtoResponse.class))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public citeDtoResponse addCite(citeDtoRequest citeDtoRequest) {
        citeModel citeModel = this.modelMapper.map(citeDtoRequest, citeModel.class);
        this.citeDao.save(citeModel);
        return this.modelMapper.map(citeModel, citeDtoResponse.class);
    }
}