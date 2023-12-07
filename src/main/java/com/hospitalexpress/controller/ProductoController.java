package com.hospitalexpress.controller;

import com.hospitalexpress.model.Producto;
import com.hospitalexpress.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/findProductoByNombre/{nombre}")
    public String findProductoByNombre(Model model, @PathVariable String nombre) {
        try {
            Producto producto = productoService.getProductoByNombre(nombre);
            model.addAttribute("producto", producto);
        } catch (Exception e) {
            model.addAttribute("productoNoEncontrado", true);
        }
        return "producto/producto";
    }
    
    @GetMapping("/productos")
public String findProductos(Model model) {
    try {
        List<Producto> listProductos = productoService.getProductos();
        if (listProductos != null) {
            model.addAttribute("productos", listProductos);
        } else {
            model.addAttribute("listaVacia", true);
        }
    } catch (Exception e) {
        model.addAttribute("listaVacia", true);
    }
    return "producto/productos";
}

    
    @GetMapping("/producto/insertar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());

        return "producto/insertar";
    }

    @PostMapping("/producto/insertar")
    public String insertarProducto(@ModelAttribute Producto producto, Model model) {
        productoService.insertarProducto(producto.getNombre(), producto.getDescripcion(), producto.getCantidad(), producto.getPrecio());
        model.addAttribute("mensaje", "Producto insertado exitosamente");

        return "producto/insertar";
    }
    
    @GetMapping("/producto/eliminar/{idProducto}")
public String eliminarProducto(Model model, @PathVariable Integer idProducto) {
    try {
        String result = productoService.eliminarProducto(idProducto);
        model.addAttribute("resultado", result);
    } catch (Exception e) {
        model.addAttribute("error", true);
    }
    return "redirect:/productos";
}


}