/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Cata
 */
    
@Table(name = "Cita", schema = "hospitalexpress")
@NamedStoredProcedureQuery(name = "Cita.getCitaById", procedureName = "SP_CONSULTAR_CITA", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_cita", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_tipo", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_fecha_hora", type = Date.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_estado", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)})

@NamedStoredProcedureQuery(name = "Cita.InsertarCita",procedureName = "SP_INSERTAR_CITA",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_paciente", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_tipo", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fecha_hora", type = Date.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "d_estado", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "d_resultado", type = String.class)
    }
)

@NamedStoredProcedureQuery(
    name = "Cita.eliminarCita",
    procedureName = "SP_ELIMINAR_CITA",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_cita", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)
    
@Entity
@Getter
@Setter
public class Cita {
    @Id
    private Integer id_cita;
    
    @OneToOne
    @JoinColumn(name = "id_doctor")
    private Cita doctor;
    
    @OneToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    
    private String tipo, estado;
    private Date fecha_hora; 
    
}
