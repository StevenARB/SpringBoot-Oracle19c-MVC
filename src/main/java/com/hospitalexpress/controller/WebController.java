/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.controller;

import com.hospitalexpress.service.DoctorService;
import com.hospitalexpress.service.PacienteService;
import com.hospitalexpress.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

/**
 *
 * @author retan
 */
@Controller
public class WebController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        try {
            model.addAttribute("numeroUsuarios", usuarioService.getNumeroUsuarios());
            model.addAttribute("numeroPacientes", pacienteService.getNumeroPacientes());
            model.addAttribute("numeroDoctores", doctorService.getNumeroDoctores());
        } catch (Exception e) {
        }
        return "admin";
    }

    @GetMapping("/farmacia")
    public String farmacia() {
        return "farmacia";
    }

}
