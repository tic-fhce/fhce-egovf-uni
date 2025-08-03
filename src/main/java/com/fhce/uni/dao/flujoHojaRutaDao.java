package com.fhce.uni.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.fhce.uni.model.flujoHojaRutaModel;

public interface flujoHojaRutaDao extends JpaRepository<flujoHojaRutaModel, Long> {
    
    @Query(value = "SELECT * FROM flujo_hoja_ruta WHERE _01id_hoja_ruta = ? ORDER BY _02orden_aprobacion", nativeQuery = true)
    List<flujoHojaRutaModel> findByHojaRutaId(Long idHojaRuta);
    
    @Query(value = "SELECT * FROM flujo_hoja_ruta WHERE _04id_usuario = ? AND _05estado = 'PENDIENTE'", nativeQuery = true)
    List<flujoHojaRutaModel> findPendientesPorUsuario(Long idUsuario);
}