
package com.hospitalexpress.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Productos", schema = "hospitalexpress")
@Getter
@Setter   
@NamedStoredProcedureQuery(
    name = "Producto.getProductoByNombre",
    procedureName = "SP_CONSULTAR_PRODUCTOS",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_id_producto", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_descripcion", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_cantidad", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_precio", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)

@NamedStoredProcedureQuery(
    name = "Producto.InsertarProducto",
    procedureName = "SP_INSERTAR_PRODUCTOS",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_descripcion", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cantidad", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_precio", type = BigDecimal.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_resultado", type = String.class)
    }
)

public class Producto {

    @Id
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "cantidad")
    private Integer  cantidad;

    @Column(name = "precio")
    private BigDecimal precio;

}