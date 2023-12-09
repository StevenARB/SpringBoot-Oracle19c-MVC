package com.hospitalexpress.repository;

import com.hospitalexpress.model.Producto;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Procedure(name = "Producto.getProductoByNombre")
    Map<String, Object> getProductoByNombre(@Param("p_nombre") String nombre);

    @Procedure(name = "Producto.getProductoById")
    Map<String, Object> getProductoById(@Param("p_id_producto") Integer id);

    @Procedure(name = "Producto.getProductos")
    List<Object[]> getProductos();

    @Procedure(name = "Producto.insertarProducto")
    String insertarProducto(
            @Param("p_nombre") String nombre,
            @Param("p_descripcion") String descripcion,
            @Param("p_cantidad") Integer cantidad,
            @Param("p_precio") BigDecimal precio
    );

    @Procedure(name = "Producto.eliminarProducto")
    String eliminarProducto(@Param("p_id_producto") Integer id);

    @Procedure(name = "Producto.actualizarProducto")
    String actualizarProducto(
            @Param("p_id_producto") Integer idProducto,
            @Param("p_nombre") String nombre,
            @Param("p_descripcion") String descripcion,
            @Param("p_cantidad") Integer cantidad,
            @Param("p_precio") BigDecimal precio
    );

}
