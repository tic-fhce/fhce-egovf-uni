package com.fhce.uni.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.fhce.uni.model.notaModel;

public interface notaDao extends JpaRepository<notaModel, Long> {
    
    @Query(value = "SELECT * FROM nota WHERE _04id_usuario_creador = ?", nativeQuery = true)
    List<notaModel> findByUsuarioCreador(Long idUsuario);
    
    @Query(value = "SELECT * FROM nota WHERE _02id_unidad_origen = ?", nativeQuery = true)
    List<notaModel> findByUnidadOrigen(Long idUnidad);
    
    @Query(value = "SELECT * FROM nota WHERE _03id_unidad_destino = ?", nativeQuery = true)
    List<notaModel> findByUnidadDestino(Long idUnidad);
    
    Optional<notaModel> findByCite(String cite);
}