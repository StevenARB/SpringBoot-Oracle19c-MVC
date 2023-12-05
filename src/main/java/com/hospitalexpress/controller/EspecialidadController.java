package com.hospitalexpress.controller;

import com.hospitalexpress.model.Especialidad;
import com.hospitalexpress.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping("/findEspecialidadById/{id_especialidad}")
    public String findEspecialidadById(Model model, @PathVariable Integer id_especialidad) {
        try {
            if (especialidadService.getEspecialidadById(id_especialidad) != null) {
                Especialidad especialidad = especialidadService.getEspecialidadById(id_especialidad);
                System.out.println(especialidad);
                model.addAttribute("especialidad", especialidad);
            } else {
                model.addAttribute("especialidadNoEncontrada", true);
            }
        } catch (Exception e) {
            model.addAttribute("especialidadNoEncontrada", true);
        }
        return "especialidad/especialidad";
    }
}