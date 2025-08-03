package com.fhce.uni.service;

import java.util.List;
import com.fhce.uni.dto.tipoNotaDtoRequest;
import com.fhce.uni.dto.tipoNotaDtoResponse;

public interface tipoNotaService {
    List<tipoNotaDtoResponse> getTiposNota();
    tipoNotaDtoResponse addTipoNota(tipoNotaDtoRequest tipoNotaDtoRequest);
}