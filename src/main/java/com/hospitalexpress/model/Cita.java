/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Cata
 */

@Entity
@Table(name = "cita")
@Getter
@Setter
public class Cita {
    
    @Id
    private Integer id_cita;
    
    @OneToOne
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;
    
    @OneToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    
    private String tipo, estado;
    private Date fecha_hora; 
    
}
