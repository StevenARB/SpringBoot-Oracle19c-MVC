/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.controller;

import com.hospitalexpress.model.Producto;
import com.hospitalexpress.model.Usuario;
import com.hospitalexpress.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

/**
 *
 * @author retan
 */
@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario/insertar")
    public String insertarUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/insertar";
    }

    @PostMapping("/usuario/insertar")
    public String insertarUsuario(Model model, @ModelAttribute Usuario usuario) {
        try {
            String result = usuarioService.insertarUsuario(usuario.getUsername(), usuario.getPassword(), usuario.getRol(), usuario.getEstado());
            model.addAttribute("resultado", result);
        } catch (Exception e) {
            model.addAttribute("error", true);
        }
        return "usuario/insertar";
    }

    @GetMapping("/usuarios")
    public String findUsuarios(Model model) {
        try {
            List<Usuario> listUsuario = usuarioService.getUsuarios();
            if (listUsuario != null) {
                model.addAttribute("usuarios", listUsuario);
            } else {
                model.addAttribute("listaVacia", true);
            }
        } catch (Exception e) {
            model.addAttribute("listaVacia", true);
        }
        return "usuario/usuarios";
    }

    @GetMapping("/findUsuarioByUsername/{username}")
    public String findUsuarioByUsername(Model model, @PathVariable String username) {
        try {
            Usuario usuario = usuarioService.getUsuarioByUsername(username);
            if (usuario != null) {
                model.addAttribute("usuario", usuario);
            } else {
                model.addAttribute("usuarioNoEncontrado", true);
            }
        } catch (Exception e) {
            model.addAttribute("usuarioNoEncontrado", true);
        }
        return "usuarios";
    }

    @GetMapping("/usuario/actualizar/{id}")
    public String findUsuarioByIdToUpdate(Model model, @PathVariable Integer id) {
        try {
            Usuario usuario = usuarioService.getUsuarioById(id);
            if (usuario != null) {
                model.addAttribute("usuario", usuario);
            } else {
                model.addAttribute("usuarioNoEncontrado", true);
            }
        } catch (Exception e) {
            model.addAttribute("usuarioNoEncontrado", true);
        }
        return "usuario/actualizar";
    }

    @PostMapping("/usuario/actualizar/{id}")
    public String insertarUsuario(Model model, @PathVariable Integer id, @ModelAttribute Usuario usuario) {
        try {
            String result = usuarioService.actualizarUsuario(id, usuario.getUsername(), usuario.getPassword(), usuario.getRol(), usuario.getEstado());
            model.addAttribute("resultado", result);
        } catch (Exception e) {
            model.addAttribute("error", true);
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/usuario/eliminar/{username}")
    public String eliminarUsuario(Model model, @PathVariable String username) {
        try {
            String result = usuarioService.eliminarUsuario(username);
            model.addAttribute("resultado", result);
        } catch (Exception e) {
            model.addAttribute("error", true);
        }
        return "redirect:/usuarios";
    }
}
