/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hospitalexpress.repository;

import com.hospitalexpress.model.Doctor;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;


public interface DoctorRepository extends JpaRepository<Doctor, Long>{
       
    @Procedure(name = "Doctor.getDoctorByNombre")
    Map<String, Object> getDoctorByNombre(@Param("d_nombre") String nombre);

    

}
