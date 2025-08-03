package com.fhce.uni.service;

import java.util.List;
import com.fhce.uni.dto.citeDtoRequest;
import com.fhce.uni.dto.citeDtoResponse;

public interface citeService {
    List<citeDtoResponse> getCites();
    citeDtoResponse addCite(citeDtoRequest citeDtoRequest);
}