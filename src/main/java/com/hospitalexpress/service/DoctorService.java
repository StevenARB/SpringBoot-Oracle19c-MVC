package com.hospitalexpress.service;

import com.hospitalexpress.model.Doctor;
import com.hospitalexpress.repository.DoctorRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public Doctor getDoctorById(Integer id_doctor) {
        try {
            Map<String, Object> result = doctorRepository.getDoctorById(id_doctor);
            if (result.get("d_resultado").equals("EXITO") && result != null && !result.isEmpty()) {
                Doctor doctor = new Doctor();
                doctor.setId(id_doctor);
                doctor.setNombre((String) result.get("d_nombre"));
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

