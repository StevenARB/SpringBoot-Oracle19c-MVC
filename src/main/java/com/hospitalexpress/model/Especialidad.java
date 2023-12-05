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
public class Especialidad {

    @Id
    @Column(name = "id_especialidad")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;
}
