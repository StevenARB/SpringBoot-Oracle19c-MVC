package com.hospitalexpress.service;


import com.hospitalexpress.model.Especialidad;
import com.hospitalexpress.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Transactional(readOnly = true)
    public Especialidad getEspecialidadById(Integer idEspecialidad) {
        try {
            Map<String, Object> result = especialidadRepository.getEspecialidadById(idEspecialidad);
            if (result.get("p_resultado").equals("EXITO") && result != null && !result.isEmpty()) {
                Especialidad especialidad = new Especialidad();
                especialidad.setId(idEspecialidad);
                especialidad.setNombre((String) result.get("p_nombre"));
                especialidad.setDescripcion((String) result.get("p_descripcion"));

                System.out.println(especialidad.getId());

                return especialidad;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
