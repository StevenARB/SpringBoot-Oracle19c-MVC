package com.hospitalexpress.service;


import com.hospitalexpress.model.Producto;
import com.hospitalexpress.repository.ProductoRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
                producto.setId((Integer) result.get("p_id_producto"));
                producto.setDescripcion((String) result.get("p_descripcion"));
                producto.setCantidad((Integer) result.get("p_cantidad"));
                producto.setPrecio((BigDecimal) result.get("p_precio"));

                System.out.println(producto.getId());

                return producto;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

 @Transactional(readOnly = true)
public List<Producto> getProductos() {
    try {
        List<Object[]> resultList = productoRepository.getProductos();
        List<Producto> productos = new ArrayList<>();

        for (Object[] result : resultList) {
            BigDecimal idProducto = (BigDecimal) result[0];
            String nombre = (String) result[1];
            String descripcion = (String) result[2];
            BigDecimal cantidad = (BigDecimal) result[3];
            BigDecimal precio = (BigDecimal) result[4];

            Producto producto = new Producto();
            producto.setId(idProducto.intValue());
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setCantidad(cantidad.intValue());
            producto.setPrecio(precio);

            productos.add(producto);
        }

        if (!productos.isEmpty()) {
            return productos;
        } else {
            return null;
        }

    } catch (Exception e) {
        return null;
    }
}
@Transactional
public String insertarProducto(String nombre, String descripcion, Integer cantidad, BigDecimal precio) {
    try {
        String result = productoRepository.insertarProducto(nombre, descripcion, cantidad, precio);
        return result;
    } catch (Exception e) {
        return null;
    }
}
    
    @Transactional
public String eliminarProducto(Integer id) {
    try {
        String result = productoRepository.eliminarProducto(id);
        return result;
    } catch (Exception e) {
        return null;
    }
}

    

}