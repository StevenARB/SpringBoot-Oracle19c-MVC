package com.hospitalexpress.service;

import com.hospitalexpress.model.Especialidad;
import com.hospitalexpress.repository.EspecialidadRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Transactional
    public String insertarEspecialidad(String nombre, String descripcion) {
        try {
            String result = especialidadRepository.insertarEspecialidad(nombre, descripcion);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

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

    @Transactional(readOnly = true)
    public List<Especialidad> getEspecialidades() {
        try {
            List<Object[]> resultList = especialidadRepository.getEspecialidades();
            List<Especialidad> especialidades = new ArrayList<>();

            for (Object[] result : resultList) {
                BigDecimal idEspecialidad = (BigDecimal) result[0];
                String nombre = (String) result[1];
                String descripcion = (String) result[2];

                Especialidad especialidad = new Especialidad();
                especialidad.setId(idEspecialidad.intValue());
                especialidad.setNombre(nombre);
                especialidad.setDescripcion(descripcion);

                especialidades.add(especialidad);
            }

            if (!especialidades.isEmpty()) {
                return especialidades;
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public String actualizarEspecialidad(Integer id, String nombre, String descripcion) {
        try {
            String result = especialidadRepository.actualizarEspecialidad(id, nombre, descripcion);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public String eliminarEspecialidad(Integer id) {
        try {
            String result = especialidadRepository.eliminarEspecialidad(id);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
