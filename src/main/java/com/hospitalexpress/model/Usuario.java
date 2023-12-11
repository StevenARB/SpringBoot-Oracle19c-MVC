/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
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
@Table(name = "usuarios")
@Getter
@Setter

@NamedStoredProcedureQuery(name = "Usuario.insertarUsuario", procedureName = "SP_INSERTAR_USUARIO", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rol", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_estado", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)})

@NamedStoredProcedureQuery(name = "Usuario.getUsuarioById", procedureName = "SP_CONSULTAR_USUARIO_ID", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_usuario", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_email", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_password", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_rol", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_estado", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)})

@NamedStoredProcedureQuery(name = "Usuario.getUsuarioByEmail", procedureName = "SP_CONSULTAR_USUARIO_EMAIL", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_id_usuario", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_rol", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_estado", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)})

@NamedStoredProcedureQuery(name = "Usuario.getUsuarios", procedureName = "SP_CONSULTAR_USUARIOS", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_cursor", type = Object.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)})

@NamedStoredProcedureQuery(name = "Usuario.actualizarUsuario", procedureName = "SP_ACTUALIZAR_USUARIO", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_usuario", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rol", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_estado", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)})

@NamedStoredProcedureQuery(name = "Usuario.eliminarUsuario", procedureName = "SP_ELIMINAR_USUARIO", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)})

@NamedStoredProcedureQuery(name = "Usuario.getNumeroUsuarios", procedureName = "SP_GET_NUMERO_USUARIOS", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.INOUT, name = "p_resultado", type = Integer.class)})

public class Usuario {

    @Id
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "rol")
    private String rol;

    @Column(name = "estado")
    private String estado;

}
