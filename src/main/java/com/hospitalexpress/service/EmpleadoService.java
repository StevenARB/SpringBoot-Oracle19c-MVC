/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.service;

import com.hospitalexpress.model.Empleado;
import com.hospitalexpress.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 * @author retan
 */
@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> getAll() {
        return empleadoRepository.findAll();
    }

    public void imprimirEmpleadosEnConsola() {
        List<Empleado> empleados = getAll();

        System.out.println("Datos de empleados:");

        for (Empleado empleado : empleados) {
            System.out.println("ID: " + empleado.getEmployeeId());
            System.out.println("Nombre: " + empleado.getFirstName() + " " + empleado.getLastName());
            // Imprime otros atributos según sea necesario
            System.out.println("---------------------------------");
        }
    }

}
