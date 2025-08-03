package com.fhce.uni.service;

import java.util.List;
import com.fhce.uni.dto.notificacionDtoRequest;
import com.fhce.uni.dto.notificacionDtoResponse;

public interface notificacionService {
    List<notificacionDtoResponse> getNotificaciones();
    notificacionDtoResponse addNotificacion(notificacionDtoRequest notificacionDtoRequest);
}