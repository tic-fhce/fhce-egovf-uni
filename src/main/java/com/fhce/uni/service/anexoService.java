package com.fhce.uni.service;

import java.util.List;
import com.fhce.uni.dto.anexoDtoRequest;
import com.fhce.uni.dto.anexoDtoResponse;

public interface anexoService {
    List<anexoDtoResponse> getAnexos();
    anexoDtoResponse addAnexo(anexoDtoRequest anexoDtoRequest);
}