package com.fhce.uni.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.fhce.uni.dao.flujoHojaRutaDao;
import com.fhce.uni.dto.flujoHojaRutaDtoRequest;
import com.fhce.uni.dto.flujoHojaRutaDtoResponse;
import com.fhce.uni.model.flujoHojaRutaModel;
import com.fhce.uni.service.flujoHojaRutaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class flujoHojaRutaServiceImpl implements flujoHojaRutaService {
    
    private final flujoHojaRutaDao flujoHojaRutaDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public List<flujoHojaRutaDtoResponse> getFlujosHojaRuta() {
        return flujoHojaRutaDao.findAll().stream()
            .map(flujo -> modelMapper.map(flujo, flujoHojaRutaDtoResponse.class))
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public flujoHojaRutaDtoResponse addFlujoHojaRuta(flujoHojaRutaDtoRequest request) {
        flujoHojaRutaModel model = modelMapper.map(request, flujoHojaRutaModel.class);
        flujoHojaRutaDao.save(model);
        return modelMapper.map(model, flujoHojaRutaDtoResponse.class);
    }
}