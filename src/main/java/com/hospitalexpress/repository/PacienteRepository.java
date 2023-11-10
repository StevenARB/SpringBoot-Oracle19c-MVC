/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.repository;

import com.hospitalexpress.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author retan
 */
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Consultas personalizadas si es necesario
}
