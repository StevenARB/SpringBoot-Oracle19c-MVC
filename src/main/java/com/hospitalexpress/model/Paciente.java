/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author retan
 */

@Entity
@Table(name = "pacientes")
@Getter
@Setter

@NamedStoredProcedureQuery(name = "Paciente.insertarPaciente", procedureName = "SP_INSERTAR_PACIENTE", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_primer_apellido", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_segundo_apellido", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_direccion", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_genero", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fecha_nac", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)})

@NamedStoredProcedureQuery(name = "Paciente.getPacienteById", procedureName = "SP_CONSULTAR_PACIENTE_ID", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_paciente", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_nombre", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_primer_apellido", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_segundo_apellido", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_email", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_direccion", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_genero", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_fecha_nac", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)})

@NamedStoredProcedureQuery(name = "Paciente.getPacientes", procedureName = "SP_CONSULTAR_PACIENTES", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_cursor", type = Object.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)})

@NamedStoredProcedureQuery(name = "Paciente.actualizarPaciente", procedureName = "SP_ACTUALIZAR_PACIENTE", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_paciente", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_primer_apellido", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_segundo_apellido", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_direccion", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_genero", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fecha_nac", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)})

@NamedStoredProcedureQuery(name = "Paciente.eliminarPaciente", procedureName = "SP_ELIMINAR_PACIENTE", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)})

public class Paciente {

    @Id
    @Column(name = "id_paciente")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "primer_apellido")
    private String primerApellido;
    
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "direccion")
    private String direccion;

    @Column(name = "genero")
    private String genero;

    @Column(name = "fecha_nac")
    private String fechaNac;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
