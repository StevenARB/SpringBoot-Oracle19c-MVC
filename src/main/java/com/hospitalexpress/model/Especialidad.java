package com.hospitalexpress.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Especialidades")
@Getter
@Setter
@NamedStoredProcedureQuery(
    name = "Especialidad.getEspecialidadById",
    procedureName = "C##HospitalExpress.SP_CONSULTAR_ESPECIALIDAD",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_especialidad", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_descripcion", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)

@NamedStoredProcedureQuery(
    name = "Especialidad.getEspecialidades",
    procedureName = "SP_CONSULTAR_ESPECIALIDADES",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_cursor", type = Object.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)

@NamedStoredProcedureQuery(
    name = "Especialidad.insertarEspecialidad",
    procedureName = "SP_INSERTAR_ESPECIALIDAD",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_descripcion", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)

@NamedStoredProcedureQuery(
    name = "Especialidad.actualizarEspecialidad",
    procedureName = "SP_ACTUALIZAR_ESPECIALIDAD",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_especialidad", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_descripcion", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)

@NamedStoredProcedureQuery(
    name = "Especialidad.eliminarEspecialidad",
    procedureName = "SP_ELIMINAR_ESPECIALIDAD",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_especialidad", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)

public class Especialidad {

    @Id
    @Column(name = "id_especialidad")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;
}
