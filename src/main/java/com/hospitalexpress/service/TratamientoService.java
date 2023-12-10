package com.hospitalexpress.service;

import com.hospitalexpress.model.Tratamiento;
import com.hospitalexpress.repository.TratamientoRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TratamientoService {
    
    @Autowired
private TratamientoRepository tratamientoRepository;

@Transactional
public String insertarTratamiento(String nombre, String descripcion) {
    try {
        String result = tratamientoRepository.insertarTratamiento(nombre, descripcion);
        return result;
    } catch (Exception e) {
        return null;
    }
}

@Transactional(readOnly = true)
public Tratamiento getTratamientoById(Integer idTratamiento) {
    try {
        Map<String, Object> result = tratamientoRepository.getTratamientoById(idTratamiento);
        if (result.get("p_resultado").equals("EXITO") && result != null && !result.isEmpty()) {
            Tratamiento tratamiento = new Tratamiento();
            tratamiento.setId(idTratamiento);
            tratamiento.setNombre((String) result.get("p_nombre"));
            tratamiento.setDescripcion((String) result.get("p_descripcion"));

            System.out.println(tratamiento.getId());

            return tratamiento;
        } else {
            return null;
        }
    } catch (Exception e) {
        return null;
    }
}

@Transactional(readOnly = true)
public List<Tratamiento> getTratamientos() {
    try {
        List<Object[]> resultList = tratamientoRepository.getTratamientos();
        List<Tratamiento> tratamientos = new ArrayList<>();

        for (Object[] result : resultList) {
            BigDecimal idTratamiento = (BigDecimal) result[0];
            String nombre = (String) result[1];
            String descripcion = (String) result[2];

            Tratamiento tratamiento = new Tratamiento();
            tratamiento.setId(idTratamiento.intValue());
            tratamiento.setNombre(nombre);
            tratamiento.setDescripcion(descripcion);

            tratamientos.add(tratamiento);
        }

        if (!tratamientos.isEmpty()) {
            return tratamientos;
        } else {
            return null;
        }

    } catch (Exception e) {
        return null;
    }
}

@Transactional
public String actualizarTratamiento(Integer id, String nombre, String descripcion) {
    try {
        String result = tratamientoRepository.actualizarTratamiento(id, nombre, descripcion);
        return result;
    } catch (Exception e) {
        return null;
    }
}

@Transactional
public String eliminarTratamiento(Integer id) {
    try {
        String result = tratamientoRepository.eliminarTratamiento(id);
        return result;
    } catch (Exception e) {
        return null;
    }
}


}
