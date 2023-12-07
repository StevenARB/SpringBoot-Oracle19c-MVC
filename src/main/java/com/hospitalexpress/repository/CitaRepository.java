/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hospitalexpress.repository;

import com.hospitalexpress.model.Cita;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Cata
 */
public interface CitaRepository extends JpaRepository<Cita, Long>{
       
    @Procedure(name = "Cita.getCitaById")
    Map<String, Object> getCitaById(@Param("c_id_cita") Integer id_cita);

     @Procedure(name = "Cita.InsertarCita")
        void InsertarCita(
        @Param("p_id_paciente") Integer id_paciente,
        @Param("p_tipo") String tipo,
        @Param("p_fecha_hora") String fecha,
        @Param("p_estado") String estado,
        @Param("p_resultado") String resultado
    );
        
        @Procedure(name = "Cita.getCitas")
List<Object[]> getCitas();

    @Procedure(name = "Cita.eliminarCita")
    String eliminarCita(@Param("c_id") Integer id);
}

