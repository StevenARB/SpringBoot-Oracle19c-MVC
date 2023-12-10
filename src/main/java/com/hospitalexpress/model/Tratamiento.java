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
@Table(name = "Tratamientos")
@Getter
@Setter
@NamedStoredProcedureQuery(
    name = "Tratamiento.insertarTratamiento",
    procedureName = "C##HospitalExpress.SP_INSERTAR_TRATAMIENTO",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_descripcion", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)

@NamedStoredProcedureQuery(
    name = "Tratamiento.getTratamientoById",
    procedureName = "C##HospitalExpress.SP_CONSULTAR_TRATAMIENTO",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_tratamiento", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_descripcion", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)

@NamedStoredProcedureQuery(
    name = "Tratamiento.getTratamientos",
    procedureName = "C##HospitalExpress.SP_CONSULTAR_TRATAMIENTOS",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_cursor", type = Object.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)

@NamedStoredProcedureQuery(
    name = "Tratamiento.actualizarTratamiento",
    procedureName = "C##HospitalExpress.SP_ACTUALIZAR_TRATAMIENTO",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_tratamiento", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_descripcion", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)

@NamedStoredProcedureQuery(
    name = "Tratamiento.eliminarTratamiento",
    procedureName = "C##HospitalExpress.SP_ELIMINAR_TRATAMIENTO",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_tratamiento", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)

public class Tratamiento {
    
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;
    
    
}
