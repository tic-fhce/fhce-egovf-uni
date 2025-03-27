package com.fhce.uni.service;

import java.util.List;

import com.fhce.uni.dto.perteneceDtoRequest;
import com.fhce.uni.dto.perteneceDtoResponse;

public interface perteneceService {
	List<perteneceDtoResponse>listar();
	perteneceDtoResponse addPertenece(perteneceDtoRequest perteneceDtoRequest);

}
