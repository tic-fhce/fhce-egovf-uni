package com.fhce.uni.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.uni.dao.citeDao;
import com.fhce.uni.dao.flujoHojaRutaDao;
import com.fhce.uni.dao.hojaRutaDao;
import com.fhce.uni.dao.notaDao;
import com.fhce.uni.dao.notificacionDao;
import com.fhce.uni.dao.perteneceDao;
import com.fhce.uni.dao.unidadDao;
import com.fhce.uni.dao.usuarioDao;
import com.fhce.uni.dto.notaDtoRequest;
import com.fhce.uni.dto.notaDtoResponse;
import com.fhce.uni.model.citeModel;
import com.fhce.uni.model.flujoHojaRutaModel;
import com.fhce.uni.model.hojaRutaModel;
import com.fhce.uni.model.notaModel;
import com.fhce.uni.model.notificacionModel;
import com.fhce.uni.model.unidadModel;
import com.fhce.uni.service.notaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class notaServiceImpl implements notaService {
    
    private final notaDao notaDao;
    private final citeDao citeDao;
    private final perteneceDao perteneceDao;
    private final notificacionDao notificacionDao;
    private final unidadDao unidadDao;
    private final usuarioDao usuarioDao;
    private final hojaRutaDao hojaRutaDao;
    private final flujoHojaRutaDao flujoHojaRutaDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public List<notaDtoResponse> getNotas() {
        return this.notaDao.findAll().stream()
                .map(nota -> this.modelMapper.map(nota, notaDtoResponse.class))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public notaDtoResponse addNota(notaDtoRequest notaDtoRequest) {
        try {
            if(notaDtoRequest.getId_unidad_destino() == null || 
               notaDtoRequest.getId_usuario_creador() == null) {
                throw new IllegalArgumentException("Datos de unidad destino o usuario creador faltantes");
            }

            Long idUnidadOrigen = perteneceDao.findUnidadIdByUsuario(notaDtoRequest.getId_usuario_creador())
                .orElseThrow(() -> new RuntimeException("El usuario no pertenece a ninguna unidad activa"));

            String cite = generarCite(
                idUnidadOrigen,
                notaDtoRequest.getId_unidad_destino(),
                notaDtoRequest.getId_usuario_creador()
            );

            notaModel nota = new notaModel();
            nota.setCite(cite);
            nota.setId_unidad_origen(idUnidadOrigen);
            nota.setId_unidad_destino(notaDtoRequest.getId_unidad_destino());
            nota.setId_usuario_creador(notaDtoRequest.getId_usuario_creador());
            nota.setReferencia(notaDtoRequest.getReferencia());
            nota.setContenido(notaDtoRequest.getContenido());
            nota.setFecha_creacion(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            nota.setEstado("PENDIENTE");
            nota.setGestion(LocalDateTime.now().getYear());

            notaModel notaGuardada = notaDao.save(nota);
            
            hojaRutaModel hojaRuta = crearHojaRuta(notaGuardada);
            crearNotificacionParaSecretario(notaGuardada);
            
            generarFlujoJerarquico(notaGuardada, hojaRuta);
            return mapToDto(notaGuardada);
            
        } catch (Exception e) {
            log.error("Error al crear nota", e);
            throw new RuntimeException("Error interno al crear nota: " + e.getMessage());
        }
    }
    
    private void crearNotificacionParaSecretario(notaModel nota) {
        Long idSecretario = perteneceDao.findSecretarioByUnidadId(nota.getId_unidad_destino())
            .orElseThrow(() -> new RuntimeException("No se encontró secretario para la unidad destino"));
        
        notificacionModel notificacion = new notificacionModel();
        notificacion.setId_usuario(idSecretario);
        notificacion.setId_flujo_hoja_ruta(null);
        notificacion.setTipo("NUEVA_NOTA");
        notificacion.setMensaje("Tiene una nueva nota con CITE: " + nota.getCite());
        notificacion.setFecha_hora(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        notificacion.setLeido(false);
        notificacion.setAccion_url("/notas/ver/" + nota.getId());
        
        notificacionDao.save(notificacion);
    }
    
    private notaDtoResponse mapToDto(notaModel nota) {
        notaDtoResponse response = new notaDtoResponse();
        response.setId(nota.getId());
        response.setCite(nota.getCite());
        response.setId_unidad_origen(nota.getId_unidad_origen());
        response.setId_unidad_destino(nota.getId_unidad_destino());
        response.setId_usuario_creador(nota.getId_usuario_creador());
        response.setFecha_creacion(nota.getFecha_creacion());
        response.setReferencia(nota.getReferencia());
        response.setContenido(nota.getContenido());
        response.setEstado(nota.getEstado());
        response.setGestion(nota.getGestion());
        response.setId_tipo_nota(nota.getId_tipo_nota());
        return response;
    }
    
    private String generarCite(Long idUnidadOrigen, Long idUnidadDestino, Long idUsuario) {
        try {
            int gestionActual = LocalDateTime.now().getYear();

            unidadModel unidadOrigen = unidadDao.findById(idUnidadOrigen)
                .orElseThrow(() -> new RuntimeException("Unidad origen no encontrada con id: " + idUnidadOrigen));

            List<String> jerarquia = new ArrayList<>();
            unidadModel actual = unidadOrigen;

            while (actual != null) {
                jerarquia.add(0, actual.getSigla());

                String nombrePadre = actual.getDependiente();
                if (nombrePadre == null || nombrePadre.trim().isEmpty()) {
                    break; 
                }
                
                Optional<unidadModel> padreOpt = unidadDao.findByUnidad(nombrePadre);
                if (!padreOpt.isPresent()) {
                    throw new RuntimeException("No se encontró la unidad padre con nombre: " + nombrePadre);
                }

                actual = padreOpt.get();
            }
            
            String prefijo = "FHCE/" + String.join("/", jerarquia);

            Integer ultimoNumero = citeDao.findUltimoNumeroCite(prefijo, gestionActual);
            int siguienteNumero = (ultimoNumero != null) ? ultimoNumero + 1 : 1;

            String nuevoCite = String.format("%s/No %03d/%d", prefijo, siguienteNumero, gestionActual);

            citeModel nuevoCiteModel = new citeModel();
            nuevoCiteModel.setCodigo_completo(nuevoCite);
            nuevoCiteModel.setPrefijo(prefijo);
            nuevoCiteModel.setSiglaUnidad(unidadOrigen.getSigla());
            nuevoCiteModel.setNumeroSecuencial(siguienteNumero);
            nuevoCiteModel.setGestion(gestionActual);
            nuevoCiteModel.setIdUnidad(idUnidadOrigen);
            nuevoCiteModel.setIdUsuario(idUsuario);
            nuevoCiteModel.setEstado(true);

            citeDao.save(nuevoCiteModel);

            return nuevoCite;

        } catch (Exception e) {
            throw new RuntimeException("Error al generar CITE: " + e.getMessage(), e);
        }
    }

    
    
    private hojaRutaModel crearHojaRuta(notaModel nota) {
        hojaRutaModel hojaRuta = new hojaRutaModel();
        hojaRuta.setCite(nota.getCite());
        hojaRuta.setId_nota(nota.getId());
        hojaRuta.setFecha_creacion(nota.getFecha_creacion());
        hojaRuta.setEstado("EN_PROCESO");
        hojaRuta.setGestion(nota.getGestion());
        
        return hojaRutaDao.save(hojaRuta);
    }
    
    private List<unidadModel> obtenerRutaHastaRaiz(Long idUnidad) {
        List<unidadModel> ruta = new ArrayList<>();
        Optional<unidadModel> unidadOpt = unidadDao.findById(idUnidad);
        
        if (!unidadOpt.isPresent()) {
            throw new RuntimeException("Unidad no encontrada: " + idUnidad);
        }
        
        unidadModel current = unidadOpt.get();
        
        ruta.add(current);
        
        while (current.getDependiente() != null && !current.getDependiente().isEmpty()) {
            Optional<unidadModel> padreOpt = unidadDao.findByUnidad(current.getDependiente());
            if (!padreOpt.isPresent()) {
                break;
            }
            current = padreOpt.get();
            ruta.add(current);
        }
        
        return ruta;
    }
    
    private void generarFlujoJerarquico(notaModel nota, hojaRutaModel hojaRuta) {
        try {
            List<unidadModel> rutaOrigen = obtenerRutaHastaRaiz(nota.getId_unidad_origen());
            List<unidadModel> rutaDestino = obtenerRutaHastaRaiz(nota.getId_unidad_destino());
            
            if (rutaOrigen.isEmpty() || rutaDestino.isEmpty()) {
                throw new RuntimeException("No se pudo obtener la jerarquía de unidades");
            }

            int i = rutaOrigen.size() - 1;
            int j = rutaDestino.size() - 1;
            
            while (i >= 0 && j >= 0 && 
                   rutaOrigen.get(i).getId().equals(rutaDestino.get(j).getId())) {
                i--;
                j--;
            }
            
            List<unidadModel> rutaCompleta = new ArrayList<>();

            for (int idx = 0; idx <= i; idx++) {
                rutaCompleta.add(rutaOrigen.get(idx));
            }

            for (int idx = j; idx >= 0; idx--) {
                if (!rutaCompleta.contains(rutaDestino.get(idx))) {
                    rutaCompleta.add(rutaDestino.get(idx));
                }
            }
            
            if (!rutaCompleta.get(0).getId().equals(nota.getId_unidad_origen())) {
                Collections.reverse(rutaCompleta);
            }

            int orden = 1;
            for (unidadModel unidad : rutaCompleta) {
                flujoHojaRutaModel flujo = new flujoHojaRutaModel();
                flujo.setId_hoja_ruta(hojaRuta.getId());
                flujo.setOrden_aprobacion(orden++);
                flujo.setId_unidad(unidad.getId());
                flujo.setEstado("PENDIENTE");
                flujo.setFechaRecibido(null);
                
                this.flujoHojaRutaDao.save(flujo);
            }

        } catch (Exception e) {
            log.error("Error al generar flujo jerárquico", e);
            throw new RuntimeException("Error al generar flujo jerárquico: " + e.getMessage());
        }
    }
}