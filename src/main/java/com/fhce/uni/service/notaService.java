package com.fhce.uni.service;

import java.util.List;
import com.fhce.uni.dto.notaDtoRequest;
import com.fhce.uni.dto.notaDtoResponse;

public interface notaService {
    List<notaDtoResponse> getNotas();
    notaDtoResponse addNota(notaDtoRequest notaDtoRequest);
}