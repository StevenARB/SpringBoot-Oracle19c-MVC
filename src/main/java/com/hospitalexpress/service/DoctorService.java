package com.hospitalexpress.service;

import com.hospitalexpress.model.Doctor;
import com.hospitalexpress.repository.DoctorRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor getDoctorByNombre(String nombre) {
        try {
            Map<String, Object> result = doctorRepository.getDoctorByNombre(nombre);

            if (result != null && !result.isEmpty()) {
                Doctor doctor = new Doctor();
                doctor.setNombre(nombre);
                doctor.setId((Integer) result.get("d_id_doctor"));
                doctor.setDireccion((String) result.get("d_direccion"));
                doctor.setTelefono((String) result.get("d_telefono"));
                doctor.setEstado((String) result.get("d_estado"));

                System.out.println(doctor.getId());

                return doctor;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}

