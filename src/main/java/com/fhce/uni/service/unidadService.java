package com.fhce.uni.service;

import java.util.List;
import com.fhce.uni.dto.unidadDtoRequest;
import com.fhce.uni.dto.unidadDtoResponse;

public interface unidadService {
    List<unidadDtoResponse> getUnidades();
    unidadDtoResponse addUnidad(unidadDtoRequest unidadDtoRequest);
}