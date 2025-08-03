package com.fhce.uni.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.uni.dao.usuarioDao;
import com.fhce.uni.dto.usuarioDtoRequest;
import com.fhce.uni.dto.usuarioDtoResponse;
import com.fhce.uni.model.usuarioModel;
import com.fhce.uni.service.usuarioService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class usuarioServiceImpl implements usuarioService {
    
    private final usuarioDao usuarioDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public List<usuarioDtoResponse> getUsuarios() {
        return this.usuarioDao.findAll().stream()
                .map(usuario -> this.modelMapper.map(usuario, usuarioDtoResponse.class))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public usuarioDtoResponse addUsuario(usuarioDtoRequest usuarioDtoRequest) {
        usuarioModel usuarioModel = this.modelMapper.map(usuarioDtoRequest, usuarioModel.class);
        usuarioModel.setActivo(true); // Por defecto activo al crear
        this.usuarioDao.save(usuarioModel);
        return this.modelMapper.map(usuarioModel, usuarioDtoResponse.class);
    }
}