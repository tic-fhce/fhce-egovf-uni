package com.fhce.uni.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.fhce.uni.model.hojaRutaModel;

public interface hojaRutaDao extends JpaRepository<hojaRutaModel, Long> {
    
    @Query(value = "SELECT * FROM hoja_ruta WHERE _02id_nota = ?", nativeQuery = true)
    Optional<hojaRutaModel> findByNotaId(Long idNota);
    
    @Query(value = "SELECT * FROM hoja_ruta WHERE _01cite = ?", nativeQuery = true)
    Optional<hojaRutaModel> findByCite(String cite);
}