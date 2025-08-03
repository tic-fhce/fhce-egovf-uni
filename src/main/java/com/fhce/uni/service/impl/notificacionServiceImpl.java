package com.fhce.uni.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.uni.dao.notificacionDao;
import com.fhce.uni.dto.notificacionDtoRequest;
import com.fhce.uni.dto.notificacionDtoResponse;
import com.fhce.uni.model.notificacionModel;
import com.fhce.uni.service.notificacionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class notificacionServiceImpl implements notificacionService {
    
    private final notificacionDao notificacionDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public List<notificacionDtoResponse> getNotificaciones() {
        return this.notificacionDao.findAll().stream()
                .map(notificacion -> this.modelMapper.map(notificacion, notificacionDtoResponse.class))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public notificacionDtoResponse addNotificacion(notificacionDtoRequest notificacionDtoRequest) {
        notificacionModel notificacionModel = this.modelMapper.map(notificacionDtoRequest, notificacionModel.class);
        notificacionModel.setLeido(false); // Por defecto no leído
        this.notificacionDao.save(notificacionModel);
        return this.modelMapper.map(notificacionModel, notificacionDtoResponse.class);
    }
}