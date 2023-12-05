package com.hospitalexpress.controller;

import com.hospitalexpress.model.Producto;
import com.hospitalexpress.service.ProductoService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @PostMapping("/InsertarProducto")
    public String InsertarProducto(
        @RequestParam String nombre,
        @RequestParam String descripcion,
        @RequestParam Integer cantidad,
        @RequestParam BigDecimal precio
    ) {
        productoService.InsertarProducto(nombre, descripcion, cantidad, precio);
        return "redirect:/listaProductos";
    }

}