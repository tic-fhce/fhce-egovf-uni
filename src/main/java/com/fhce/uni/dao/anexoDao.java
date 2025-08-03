package com.fhce.uni.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.fhce.uni.model.anexoModel;

public interface anexoDao extends JpaRepository<anexoModel, Long> {
    
    @Query(value = "SELECT * FROM anexo WHERE _01id_nota = ?", nativeQuery = true)
    List<anexoModel> findByNotaId(Long idNota);
    
    @Query(value = "SELECT * FROM anexo WHERE _07id_usuario_subio = ?", nativeQuery = true)
    List<anexoModel> findByUsuarioId(Long idUsuario);
}