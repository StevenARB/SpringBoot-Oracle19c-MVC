/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.repository;

import com.hospitalexpress.model.Paciente;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author retan
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Procedure(name = "Paciente.insertarPaciente")
    String insertarPaciente(
            @Param("p_nombre") String nombre,
            @Param("p_primer_apellido") String primerApellido,
            @Param("p_segundo_apellido") String segundoApellido,
            @Param("p_email") String email,
            @Param("p_direccion") String direccion,
            @Param("p_genero") String genero,
            @Param("p_fecha_nac") String fechaNac);

    @Procedure(name = "Paciente.getPacienteById")
    Map<String, Object> getPacienteById(@Param("p_id_paciente") Integer id);

    @Procedure(name = "Paciente.getPacientes")
    List<Object[]> getPacientes();

    @Procedure(name = "Paciente.actualizarPaciente")
    String actualizarPaciente(
            @Param("p_id_paciente") Integer id,
            @Param("p_nombre") String nombre,
            @Param("p_primer_apellido") String primerApellido,
            @Param("p_segundo_apellido") String segundoApellido,
            @Param("p_email") String email,
            @Param("p_direccion") String direccion,
            @Param("p_genero") String genero,
            @Param("p_fecha_nac") String fechaNac);

    @Procedure(name = "Paciente.eliminarPaciente")
    String eliminarPaciente(@Param("p_email") String email);

    @Procedure(name = "Paciente.getNumeroPacientes")
    Integer getNumeroPacientes(@Param("p_resultado") Integer resultado);

}
