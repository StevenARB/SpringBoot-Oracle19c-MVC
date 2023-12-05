package com.hospitalexpress.controller;

import com.hospitalexpress.model.Doctor;
import com.hospitalexpress.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/findDoctorById/{id_doctor}")
    public String findDoctorById(Model model, @PathVariable Integer id_doctor) {
        try {
            if (doctorService.getDoctorById(id_doctor) != null) {
                Doctor doctor = doctorService.getDoctorById(id_doctor);
                model.addAttribute("doctor", doctor);
            } else {
                model.addAttribute("doctorNoEncontrado", true);
            }
        } catch (Exception e) {
            model.addAttribute("doctorNoEncontrado", true);
        }
        return "doctor/doctor"; 
    }
    
     @GetMapping("/doctor/insertar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("doctor", new Doctor());

        return "doctor/insertar";
    }

    @PostMapping("/doctor/insertar")
    public String insertarDoctor(@ModelAttribute Doctor doctor, Model model) {
        doctorService.insertarDoctor(doctor.getNombre(), doctor.getDireccion(), doctor.getTelefono(), doctor.getEstado());
        model.addAttribute("mensaje", "Doctor insertado exitosamente");

        return "doctor/insertar";
    }
    
}
