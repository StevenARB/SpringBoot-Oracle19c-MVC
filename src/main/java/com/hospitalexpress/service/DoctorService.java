package com.hospitalexpress.service;

import com.hospitalexpress.model.Doctor;
import com.hospitalexpress.repository.DoctorRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

            if (result.get("d_resultado").equals("EXITO")) {
                Doctor doctor = new Doctor();
                doctor.setId(id_doctor);
                doctor.setNombre((String) result.get("d_nombre"));
                doctor.setDireccion((String) result.get("d_direccion"));
                doctor.setTelefono((String) result.get("d_telefono"));
                doctor.setEstado((String) result.get("d_estado"));

                return doctor;
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Doctor> getDoctores() {
        try {
            List<Object[]> resultList = doctorRepository.getDoctores();
            List<Doctor> doctores = new ArrayList<>();

            for (Object[] result : resultList) {
                BigDecimal idDoctor = (BigDecimal) result[0];
                String nombre = (String) result[1];
                String direccion = (String) result[2];
                String telefono = (String) result[3];
                String estado = (String) result[4];

                Doctor doctor = new Doctor();
                doctor.setId(idDoctor.intValue());
                doctor.setNombre(nombre);
                doctor.setDireccion(direccion);
                doctor.setTelefono(telefono);
                doctor.setEstado(estado);

                doctores.add(doctor);
            }

            if (!doctores.isEmpty()) {
                return doctores;
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public String actualizarDoctor(Integer id, String nombre, String direccion, String telefono, String estado) {
        try {
            String result = doctorRepository.actualizarDoctor(id, nombre, direccion, telefono, estado);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public String insertarDoctor(String nombre, String direccion, String telefono, String estado) {
        try {
            String result = doctorRepository.insertarDoctor(nombre, direccion, telefono, estado);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public String eliminarDoctor(Integer id) {
        try {
            String result = doctorRepository.eliminarDoctor(id);
            return result;
        } catch (Exception e) {
            return null;
        }
    }
    
    @Transactional(readOnly = true)
    public Integer getNumeroDoctores() {
        try {
            Integer numeroDoctores = doctorRepository.getNumeroDoctores(null);
            if (numeroDoctores > 0) {
                return numeroDoctores;
            } else {
                return 0;
            }

        } catch (Exception e) {
            return 0;
        }
    }

}
