
package com.hospitalexpress.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "Medicamentos", schema = "hospitalexpress")
@Getter
@Setter
@NamedStoredProcedureQuery(
    name = "Medicamento.InsertarMedicamento",
    procedureName = "SP_INSERTAR_MEDICAMENTO",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dosis", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cantidad", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_precio", type = BigDecimal.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)
@NamedStoredProcedureQuery(
    name = "Medicamento.getMedicamentoById",
    procedureName = "SP_CONSULTAR_MEDICAMENTO",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_medicamento", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_dosis", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_cantidad", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_precio", type = BigDecimal.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)
@NamedStoredProcedureQuery(
    name = "Medicamento.getMedicamentos",
    procedureName = "SP_CONSULTAR_MEDICAMENTOS",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_cursor", type = Object.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)
@NamedStoredProcedureQuery(
    name = "Medicamento.actualizarMedicamento",
    procedureName = "SP_ACTUALIZAR_MEDICAMENTO",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_medicamento", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dosis", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cantidad", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_precio", type = BigDecimal.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)
@NamedStoredProcedureQuery(
    name = "Medicamento.eliminarMedicamento",
    procedureName = "SP_ELIMINAR_MEDICAMENTO",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_medicamento", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)



public class Medicamento {

    @Id
    @Column(name = "id_medicamento")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "dosis")
    private String dosis;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio")
    private BigDecimal precio;

}
