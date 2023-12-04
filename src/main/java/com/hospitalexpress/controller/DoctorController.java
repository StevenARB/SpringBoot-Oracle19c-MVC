package com.hospitalexpress.controller;

import com.hospitalexpress.model.Doctor;
import com.hospitalexpress.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;


@Controller
public class DoctorController {
    
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/findDoctorByNombre/{nombre}")
    public String findDoctorByNombre(Model model, @PathVariable String nombre) {
        try {
            Doctor doctor = doctorService.getDoctorByNombre(nombre);
            model.addAttribute("doctor", doctor);
        } catch (Exception e) {
            model.addAttribute("doctorNoEncontrado", true);
        }
        return "doctor"; 
    }
}
