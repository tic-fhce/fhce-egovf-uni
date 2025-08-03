package com.fhce.uni.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.uni.dao.unidadDao;
import com.fhce.uni.dto.unidadDtoRequest;
import com.fhce.uni.dto.unidadDtoResponse;
import com.fhce.uni.model.unidadModel;
import com.fhce.uni.service.unidadService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class unidadServiceImpl implements unidadService{
	
	private final unidadDao unidadDao;
	private final ModelMapper modelMapper;
	@Transactional
	public List<unidadDtoResponse>getUnidades(){
		List<unidadDtoResponse>unidades = this.unidadDao.findAll().stream()
				.map(unidad-> this.modelMapper.map(unidad, unidadDtoResponse.class))
				.collect(Collectors.toList());
		
		return unidades;
	}
	
	@Transactional
	public unidadDtoResponse addUnidad(unidadDtoRequest unidadDtoRequest) {
		unidadModel unidadModel = this.modelMapper.map(unidadDtoRequest, unidadModel.class);
		this.unidadDao.save(unidadModel);
		return (this.modelMapper.map(unidadModel, unidadDtoResponse.class));
	}
}