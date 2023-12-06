package com.hospitalexpress.service;


import com.hospitalexpress.model.Producto;
import com.hospitalexpress.repository.ProductoRepository;
import java.math.BigDecimal;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

@Autowired
private ProductoRepository productoRepository;

@Transactional(readOnly = true)
    public Producto getProductoByNombre(String nombre) {
        try {
            Map<String, Object> result = productoRepository.getProductoByNombre(nombre);
            if (result != null && !result.isEmpty()) {
                Producto producto = new Producto();
                producto.setNombre((String) nombre);
                producto.setIdProducto((Integer) result.get("p_id_producto"));
                producto.setDescripcion((String) result.get("p_descripcion"));
                producto.setCantidad((Integer) result.get("p_cantidad"));
                producto.setPrecio((BigDecimal) result.get("p_precio"));

                System.out.println(producto.getIdProducto());

                return producto;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void insertarProducto(String nombre, String descripcion, Integer cantidad, BigDecimal precio) {
        try {
            String resultado = null; 
            productoRepository.InsertarProducto(nombre, descripcion, cantidad, precio, resultado);
        } catch (Exception e) {
            
        }
    }

}