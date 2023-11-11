/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hospitalexpress.repository;

import com.hospitalexpress.model.Doctor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;


public interface DoctorRepository extends JpaRepository<Doctor, Long>{
       
    @Procedure(name = "SP_INSERTAR_DOCTOR")
    String insertarDoctor(
            @Param("d_nombre") String nombre,
            @Param("d_direccion") String direccion,
            @Param("d_telefono") String telefono,
            @Param("d_estado") String estado
    );
    
    @Procedure(name = "SP_ACTUALIZAR_DOCTOR")
    String actualizarDoctor(
            @Param("d_id") Long id,
            @Param("d_nombre") String nombre,
            @Param("d_direccion") String direccion,
            @Param("d_telefono") String telefono,
            @Param("d_estado") String estado
    );

    @Procedure(name = "SP_ELIMINAR_DOCTOR")
    String eliminarDoctor(@Param("d_id") Long id);
    
    @Procedure(name = "SP_INCREMENTAR_PACIENTES_ATENDIDOS")
    String incrementarPacientesAtendidos(
            @Param("d_id_doctor") Long idDoctor, 
            @Param("d_cantidad") Long cantidad);
     
    @Procedure(name = "SP_CAMBIAR_ESTADO_DOCTOR")
    String cambiarEstadoDoctor(
        @Param("d_id_doctor") Long idDoctor, 
        @Param("d_nuevo_estado") String nuevoEstado);
    
    @Query(nativeQuery = true, value = "CALL SP_OBTENER_DOCTORES_POR_ESTADO(:d_estado)")
    List<Doctor> obtenerDoctoresPorEstado(
            @Param("d_estado") String estado);
     
    @Query(nativeQuery = true, value = "CALL SP_OBTENER_CANTIDAD_DOCTORES_POR_ESTADO(:p_estado, :p_cantidad)")
    Long obtenerCantidadDoctoresPorEstado(
            @Param("p_estado") String estado, 
            @Param("p_cantidad") Long cantidad);
}
