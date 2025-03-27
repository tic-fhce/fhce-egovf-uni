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
public class perteneceServiceImpl implements perteneceService{
	
	private final perteneceDao perteneceDao;
	private final ModelMapper modelMapper;
	
	@Transactional
	public List<perteneceDtoResponse>listar(){
		List<perteneceDtoResponse> pertenece = this.perteneceDao.findAll().stream()
				.map(per->this.modelMapper.map(per, perteneceDtoResponse.class))
				.collect(Collectors.toList());
		return (pertenece);
	}
	
	@Transactional
	public perteneceDtoResponse addPertenece(perteneceDtoRequest perteneceDtoRequest) {
		perteneceModel perteneceModel = new perteneceModel();
		perteneceModel.setCif(perteneceDtoRequest.getCif());
		perteneceModel.setEstado(true);
		perteneceModel.setFecha(perteneceDtoRequest.getFecha());
		perteneceModel.setId_unidad(perteneceDtoRequest.getId_unidad());
		perteneceModel.setGestion(perteneceDtoRequest.getGestion());
		this.perteneceDao.save(perteneceModel);
		return (this.modelMapper.map(perteneceModel, perteneceDtoResponse.class));
	}
	
	
}
