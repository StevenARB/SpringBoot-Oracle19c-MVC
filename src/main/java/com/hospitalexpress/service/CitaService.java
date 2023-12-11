/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.service;

import com.hospitalexpress.model.Cita;
import com.hospitalexpress.repository.CitaRepository;
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

   /** @Transactional
    public String insertarCita(Integer id, String tipo, Date fecha_hora, String estado) {
        try {
            String result = citaRepository.insertarCita(id, tipo, fecha_hora, estado);
            return result;
        } catch (Exception e) {
            return null;
        }
    }**/

    @Transactional(readOnly = true)
    public List<Cita> getCitas() {
        try {
            List<Object[]> resultList = citaRepository.getCitas();
            List<Cita> citas = new ArrayList<>();

            for (Object[] result : resultList) {
                Integer id = (Integer) result[0];
                String tipo = (String) result[1];
                Date fechaHora = (Date) result[2];
                String estado = (String) result[4];

                Cita cita = new Cita();
                /**cita.setId(id);**/
                cita.setTipo(tipo);
                /**cita.setFechaHora(fechaHora);**/
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
    }

    public Cita getCitaById(Integer id) {
        try {

            Map<String, Object> result = citaRepository.getCitaById(id);

            if (result.get("p_resultado").equals("EXITO")) {
                Cita cita = new Cita();
                /**cita.setId((Integer) id);**/
                cita.setTipo((String) result.get("p_tipo"));
                /**cita.setFechaHora((Date) result.get("p_fecha_hora"));**/
                cita.setEstado((String) result.get("p_estado"));

                return cita;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}

  /** @Transactional
    public String actualizarCita(Integer id, String tipo, Date fechaHora, String estado) {
        try {
            String result = citaRepository.actualizarCita(id, tipo, fechaHora, estado);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public String eliminarCita(String username) {
        try {
            String result = citaRepository.eliminarCita(id);
            return result;
        } catch (Exception e) {
            return null;
        }
    }
} **/