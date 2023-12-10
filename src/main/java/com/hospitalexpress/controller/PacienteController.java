/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.controller;

import com.hospitalexpress.model.Paciente;
import com.hospitalexpress.service.PacienteService;
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
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/paciente/insertar")
    public String insertarPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "paciente/insertar";
    }

    @PostMapping("/paciente/insertar")
    public String insertarPaciente(Model model, @ModelAttribute Paciente paciente) {
        try {
            String result = pacienteService.insertarPaciente(paciente.getNombre(), paciente.getPrimerApellido(), paciente.getSegundoApellido(), paciente.getEmail(), paciente.getDireccion(), paciente.getGenero(), paciente.getFechaNac());
            model.addAttribute("resultado", result);
        } catch (Exception e) {
            model.addAttribute("error", true);
        }
        return "paciente/insertar";
    }

    @GetMapping("/pacientes")
    public String findPacientes(Model model) {
        try {
            List<Paciente> listPaciente = pacienteService.getPacientes();
            if (listPaciente != null) {
                model.addAttribute("pacientes", listPaciente);
            } else {
                model.addAttribute("listaVacia", true);
            }
        } catch (Exception e) {
            model.addAttribute("listaVacia", true);
        }
        return "paciente/pacientes";
    }

    /*@GetMapping("/findPacienteByEmail/{email}")
    public String findPacienteByEmail(Model model, @PathVariable String email) {
        try {
            Paciente paciente = pacienteService.getPacienteByEmail(email);
            if (paciente != null) {
                model.addAttribute("paciente", paciente);
            } else {
                model.addAttribute("pacienteNoEncontrado", true);
            }
        } catch (Exception e) {
            model.addAttribute("pacienteNoEncontrado", true);
        }
        return "pacientes";
    }
    */
    @GetMapping("/paciente/actualizar/{id}")
    public String findPacienteByIdToUpdate(Model model, @PathVariable Integer id) {
        try {
            Paciente paciente = pacienteService.getPacienteById(id);
            if (paciente != null) {
                model.addAttribute("paciente", paciente);
            } else {
                model.addAttribute("pacienteNoEncontrado", true);
            }
        } catch (Exception e) {
            model.addAttribute("pacienteNoEncontrado", true);
        }
        return "paciente/actualizar";
    }

    @PostMapping("/paciente/actualizar/{id}")
    public String actualizarPaciente(Model model, @PathVariable Integer id, @ModelAttribute Paciente paciente) {
        try {
            String result = pacienteService.actualizarPaciente(id, paciente.getNombre(), paciente.getPrimerApellido(), paciente.getSegundoApellido(), paciente.getEmail(), paciente.getDireccion(), paciente.getGenero(), paciente.getFechaNac());
            model.addAttribute("resultado", result);
        } catch (Exception e) {
            model.addAttribute("error", true);
        }
        return "redirect:/pacientes";
    }

    @GetMapping("/paciente/eliminar/{email}")
    public String eliminarPaciente(Model model, @PathVariable String email) {
        try {
            String result = pacienteService.eliminarPaciente(email);
            model.addAttribute("resultado", result);
        } catch (Exception e) {
            model.addAttribute("error", true);
        }
        return "redirect:/pacientes";
    }
}
