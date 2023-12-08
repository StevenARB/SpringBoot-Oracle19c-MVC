
package com.hospitalexpress.service;

import com.hospitalexpress.model.Medicamento;
import com.hospitalexpress.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Service
public class MedicamentoService {
      @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Transactional
    public String insertarMedicamento(String nombre, String dosis, Integer cantidad, BigDecimal precio) {
        try {
           String result = medicamentoRepository.insertarMedicamento(nombre, dosis, cantidad, precio);
            return result;
        } catch (Exception e) {
               return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Medicamento> getMedicamentos() {
        try {
            List<Object[]> resultList = medicamentoRepository.getMedicamentos();
            List<Medicamento> medicamentos = new ArrayList<>();

            for (Object[] result : resultList) {
                Integer id = (Integer) result[0];
                String nombre = (String) result[1];
                String dosis = (String) result[2];
                Integer cantidad = (Integer) result[3];
                BigDecimal precio = (BigDecimal) result[4];

                Medicamento medicamento = new Medicamento();
                medicamento.setId(id.intValue());
                medicamento.setNombre(nombre);
                medicamento.setDosis(dosis);
                medicamento.setCantidad(cantidad);
                medicamento.setPrecio(precio);

                medicamentos.add(medicamento);
            }

           if (!medicamentos.isEmpty()) {
                return medicamentos;
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    public Medicamento getMedicamentoById(Integer id) {
        try {
            Map<String, Object> result = medicamentoRepository.getMedicamentoById(id);

            if (result.get("p_resultado").equals("EXITO")) {
                Medicamento medicamento = new Medicamento();
                medicamento.setId(id);
                medicamento.setNombre((String) result.get("p_nombre"));
                medicamento.setDosis((String) result.get("p_dosis"));
                medicamento.setCantidad((Integer) result.get("p_cantidad"));
                medicamento.setPrecio((BigDecimal) result.get("p_precio"));

                return medicamento;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public String actualizarMedicamento(Integer id, String nombre, String dosis, Integer cantidad, BigDecimal precio) {
        try {
            return medicamentoRepository.actualizarMedicamento(id, nombre, dosis, cantidad, precio);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public String eliminarMedicamento(Integer id) {
        try {
            return medicamentoRepository.eliminarMedicamento(id);
        } catch (Exception e) {
            return null;
        }
    }
}
