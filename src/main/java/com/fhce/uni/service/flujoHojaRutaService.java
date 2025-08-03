package com.fhce.uni.service;

import java.util.List;
import com.fhce.uni.dto.flujoHojaRutaDtoRequest;
import com.fhce.uni.dto.flujoHojaRutaDtoResponse;

public interface flujoHojaRutaService {
    List<flujoHojaRutaDtoResponse> getFlujosHojaRuta();
    flujoHojaRutaDtoResponse addFlujoHojaRuta(flujoHojaRutaDtoRequest flujoHojaRutaDtoRequest);
}