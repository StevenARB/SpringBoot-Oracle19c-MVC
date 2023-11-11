/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.service;

import com.hospitalexpress.model.Doctor;
import com.hospitalexpress.repository.DoctorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    
    
    @Autowired
    private DoctorRepository doctorRepository;
    
      public String insertarDoctor(String nombre, String direccion, String telefono, String estado) {
        try {
            return doctorRepository.insertarDoctor(nombre, direccion, telefono, estado);
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }
      
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }
    
     public String actualizarDoctor(Long id, String nombre, String direccion, String telefono, String estado) {
        try {
            return doctorRepository.actualizarDoctor(id, nombre, direccion, telefono, estado);
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }

    public String eliminarDoctor(Long id) {
        try {
            return doctorRepository.eliminarDoctor(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }
    public String incrementarPacientesAtendidos(Long idDoctor, Long cantidad) {
        try {
            return doctorRepository.incrementarPacientesAtendidos(idDoctor, cantidad);
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }
    public String cambiarEstadoDoctor(Long idDoctor, String nuevoEstado) {
    try {
        return doctorRepository.cambiarEstadoDoctor(idDoctor, nuevoEstado);
    } catch (Exception e) {
        e.printStackTrace();
        return "ERROR: " + e.getMessage();
        }
    }
    
    public List<Doctor> obtenerDoctoresPorEstado(String estado) {
        return doctorRepository.obtenerDoctoresPorEstado(estado);
    }
    
 public Long obtenerCantidadDoctoresPorEstado(String estado) {
        try {
            Long cantidad = 0L; //0L L para indicar que es de tipo Long
            doctorRepository.obtenerCantidadDoctoresPorEstado(estado, cantidad);
            return cantidad;
        } catch (Exception e) {
            e.printStackTrace();
            return -1L; // Valor de retorno por defecto 
        }
    }
}
