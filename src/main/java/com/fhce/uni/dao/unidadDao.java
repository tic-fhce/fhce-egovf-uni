package com.fhce.uni.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fhce.uni.model.unidadModel;

public interface unidadDao extends JpaRepository<unidadModel, Long> {
    
    Optional<unidadModel> findBySigla(String sigla);
    
    Optional<unidadModel> findByUnidad(String nombreUnidad);
}