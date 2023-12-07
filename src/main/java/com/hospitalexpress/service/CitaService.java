/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.service;

import com.hospitalexpress.model.Cita;
import com.hospitalexpress.repository.CitaRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cata
 */
@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    /*
    @Transactional(readOnly = true)
    public Cita getCitaById(Integer id_cita) {
        try {

            Map<String, Object> result = citaRepository.getCitaById(id_cita);

            if (result.get("p_resultado").equals("EXITO")) {
                Cita cita = new Cita();
                cita.setId(id_cita);
                cita.setTipo((String) result.get("p_tipo"));
                cita.setFecha((Date) result.get("p_fecha_hora"));
                cita.setEstado((String) result.get("p_estado"));

                return cita;
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }*/
    
   /* @Transactional(readOnly = true)
public List<Cita> getCitas() {
    try {
        List<Object[]> resultList = citaRepository.getCitas();
        List<Cita> citas = new ArrayList<>();

        for (Object[] result : resultList) {
            BigDecimal idCita = (BigDecimal) result[0];
            String tipo = (String) result[1];
            String fecha_hora = (String) result[2];
            String telefono = (String) result[3];
            String estado = (String) result[4];

            Cita cita = new Cita();
            cita.setId(idCita.intValue());
            cita.setTipo(tipo);
            cita.setFecha(fecha_hora);
            cita.setEstado(estado);

            citas.add(cita);
        }

            if (!citas.isEmpty()) {
                return citas;
            } else {
                return null;
            }

    } catch (Exception e) {
        return null;
    }
}*/

    
     @Transactional
    public void insertarCita(Integer id_paciente, String tipo, String fecha_hora, String estado) {
        try {
            String resultado = null; 
            citaRepository.InsertarCita(id_paciente, tipo, fecha_hora, estado, resultado);
        } catch (Exception e) {
            
        }
    }
    
    
    
    
@Transactional
public String eliminarCita(Integer id) {
    try {
        String result = citaRepository.eliminarCita(id);
        return result;
    } catch (Exception e) {
        return null;
    }
}
    
}