package com.fhce.uni.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fhce.uni.model.citeModel;

public interface citeDao extends JpaRepository<citeModel, Long> {
    
    @Query("SELECT COUNT(c) FROM citeModel c WHERE c.gestion = :gestion")
    int countByGestion(@Param("gestion") int gestion);
    
    @Query("SELECT MAX(c.numeroSecuencial) FROM citeModel c WHERE c.gestion = :gestion")
    Integer findMaxNumeroSecuencialByGestion(@Param("gestion") int gestion);
    
    @Query("SELECT MAX(c.numeroSecuencial) FROM citeModel c WHERE c.prefijo = :prefijo AND c.gestion = :gestion")
    Integer findUltimoNumeroCite(@Param("prefijo") String prefijo, @Param("gestion") int gestion);
    
    List<citeModel> findByIdUnidad(Long id_unidad);
    
}