package com.fhce.uni.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fhce.uni.model.usuarioModel;

public interface usuarioDao extends JpaRepository<usuarioModel, Long> {
    
    Optional<usuarioModel> findByCif(int cif);
    
    Optional<usuarioModel> findByEmail(String email);
}