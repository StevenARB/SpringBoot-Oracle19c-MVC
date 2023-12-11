/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hospitalexpress.repository;

import com.hospitalexpress.model.Medicamento;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;


public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>{
    
  @Procedure(name = "Medicamento.InsertarMedicamento")
    String insertarMedicamento(
        @Param("p_nombre") String nombre,
        @Param("p_dosis") String dosis,
        @Param("p_cantidad") Integer cantidad,
        @Param("p_precio") BigDecimal precio
    );

    @Procedure(name = "Medicamento.getMedicamentoById")
    Map<String, Object> getMedicamentoById(@Param("p_id_medicamento") Integer id_medicamento);

    @Procedure(name = "Medicamento.getMedicamentos")
    List<Object[]> getMedicamentos();

    @Procedure(name = "Medicamento.actualizarMedicamento")
    String actualizarMedicamento(
        @Param("p_id_medicamento") Integer id_medicamento,
        @Param("p_nombre") String nombre,
        @Param("p_dosis") String dosis,
        @Param("p_cantidad") Integer cantidad,
        @Param("p_precio") BigDecimal precio
    );

  
    @Procedure(name = "Medicamento.eliminarMedicamento")
    String eliminarMedicamento(@Param("p_id_medicamento") Integer id_medicamento);
}

