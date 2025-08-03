package com.fhce.uni.service;

import java.util.List;
import com.fhce.uni.dto.hojaRutaDtoRequest;
import com.fhce.uni.dto.hojaRutaDtoResponse;

public interface hojaRutaService {
    List<hojaRutaDtoResponse> getHojasRuta();
    hojaRutaDtoResponse addHojaRuta(hojaRutaDtoRequest hojaRutaDtoRequest);
}