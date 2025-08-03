package com.fhce.uni.service;

import java.util.List;
import com.fhce.uni.dto.usuarioDtoRequest;
import com.fhce.uni.dto.usuarioDtoResponse;

public interface usuarioService {
    List<usuarioDtoResponse> getUsuarios();
    usuarioDtoResponse addUsuario(usuarioDtoRequest usuarioDtoRequest);
}