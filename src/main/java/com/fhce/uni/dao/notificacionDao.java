package com.fhce.uni.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.fhce.uni.model.notificacionModel;

public interface notificacionDao extends JpaRepository<notificacionModel, Long> {
    
    @Query(value = "SELECT * FROM notificacion WHERE _01id_usuario = ? AND _06leido = false", nativeQuery = true)
    List<notificacionModel> findNoLeidasPorUsuario(Long idUsuario);
    
    @Query(value = "SELECT * FROM notificacion WHERE _01id_usuario = ? ORDER BY _05fecha_hora DESC", nativeQuery = true)
    List<notificacionModel> findByUsuarioId(Long idUsuario);
}