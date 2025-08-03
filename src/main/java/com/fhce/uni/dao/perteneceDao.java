package com.fhce.uni.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fhce.uni.model.perteneceModel;

public interface perteneceDao extends JpaRepository<perteneceModel, Long> {
    
    @Query(value = "SELECT * FROM pertenece WHERE _02id_usuario = ? AND _04estado = true", nativeQuery = true)
    List<perteneceModel> findActivosPorUsuario(Long idUsuario);
    
    @Query(value = "SELECT * FROM pertenece WHERE _01id_unidad = ? AND _06rol = ? AND _04estado = true", nativeQuery = true)
    List<perteneceModel> findByUnidadYRol(Long idUnidad, String rol);
    
    @Query("SELECT p.id_unidad FROM perteneceModel p WHERE p.id_usuario = :idUsuario AND p.estado = true")
    Optional<Long> findUnidadIdByUsuario(@Param("idUsuario") Long idUsuario);
    
    @Query("SELECT p.id_usuario FROM perteneceModel p WHERE p.id_unidad = :idUnidad AND p.rol = 'SECRETARIO' AND p.estado = true")
    Optional<Long> findSecretarioByUnidadId(@Param("idUnidad") Long idUnidad);
}