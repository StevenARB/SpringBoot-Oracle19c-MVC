/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.controller;

import com.hospitalexpress.model.Usuario;
import com.hospitalexpress.service.UsuarioService;
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

    @GetMapping("/findUsuarioByUsername/{username}")
    public String findUsuarioByUsername(Model model, @PathVariable String username) {
        try {
            Usuario usuario = usuarioService.getUsuarioByUsername(username);
            model.addAttribute("usuario", usuario);
        } catch (Exception e) {
            model.addAttribute("usuarioNoEncontrado", true);
        }
        return "usuario";
    }

}
