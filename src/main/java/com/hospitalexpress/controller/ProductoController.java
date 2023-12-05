package com.hospitalexpress.controller;

import com.hospitalexpress.model.Producto;
import com.hospitalexpress.service.ProductoService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    
    @GetMapping("/producto/insertar")
    public String mostrarFormulario(Model model) {
        // Aquí puedes inicializar cualquier modelo necesario para tu formulario
        model.addAttribute("producto", new Producto());

        return "producto/insertar";
    }

    @PostMapping("/producto/insertar")
    public String insertarProducto(@ModelAttribute Producto producto, Model model) {
        // Aquí puedes validar y procesar el objeto producto y luego insertarlo en la base de datos
        productoService.insertarProducto(producto.getNombre(), producto.getDescripcion(), producto.getCantidad(), producto.getPrecio());

        // Agrega un mensaje de éxito al modelo
        model.addAttribute("mensaje", "Producto insertado exitosamente");

        // Redirige a la página de inserción
        return "producto/insertar";
    }
    
    
    
//            @GetMapping("/producto/insertar")
//    public String mostrarFormulario() {
//        return "producto/insertar";
//    }
//    
//    
//  @PostMapping("/producto/insertar")
//    public String insertarProducto(@RequestParam String nombre, @RequestParam String descripcion,
//                                   @RequestParam Integer cantidad, @RequestParam BigDecimal precio) {
//        productoService.insertarProducto(nombre, descripcion, cantidad, precio);
//        return "producto/insertar";
//    }

}