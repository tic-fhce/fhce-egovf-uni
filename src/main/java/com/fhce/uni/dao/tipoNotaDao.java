package com.fhce.uni.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fhce.uni.model.tipoNotaModel;

public interface tipoNotaDao extends JpaRepository<tipoNotaModel, Long> {
    Optional<tipoNotaModel> findByNombreTipo(String nombreTipo);
}