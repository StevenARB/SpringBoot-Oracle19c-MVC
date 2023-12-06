/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.controller;

import com.hospitalexpress.model.Usuario;
import com.hospitalexpress.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

/**
 *
 * @author retan
 */
@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public String findUsuarios(Model model) {
        try {
            List<Usuario> listUsuario = usuarioService.getUsuarios();
            if (listUsuario != null) {
                model.addAttribute("usuarios", listUsuario);
            } else {
                model.addAttribute("usuarioNoEncontrado", true);
            }
        } catch (Exception e) {
            model.addAttribute("usuarioNoEncontrado", true);
        }
        return "usuarios";
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

}
