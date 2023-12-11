
package com.hospitalexpress.controller;

import com.hospitalexpress.model.Medicamento;
import com.hospitalexpress.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping("/medicamento/insertar")
    public String insertarMedicamento(Model model) {
        model.addAttribute("medicamento", new Medicamento());
        return "medicamento/insertar";
    }

    @PostMapping("/medicamento/insertar")
    public String insertarMedicamento(Model model, @ModelAttribute Medicamento medicamento) {
        try {
            String result = medicamentoService.insertarMedicamento(
                medicamento.getNombre(),
                medicamento.getDosis(),
                medicamento.getCantidad(),
                medicamento.getPrecio()
            );
            model.addAttribute("resultado", result);
        } catch (Exception e) {
            model.addAttribute("error", true);
        }
        return "medicamento/insertar";
    }

    @GetMapping("/medicamentos")
    public String findMedicamentos(Model model) {
        try {
            List<Medicamento> listMedicamento = medicamentoService.getMedicamentos();
            if (listMedicamento != null) {
                model.addAttribute("medicamentos", listMedicamento);
            } else {
                model.addAttribute("listaVacia", true);
            }
        } catch (Exception e) {
            model.addAttribute("listaVacia", true);
        }
        return "medicamento/medicamentos";
    }

    @GetMapping("/findMedicamentoByIdToUpdate/{id}")
    public String findMedicamentoByIdToUpdate(Model model, @PathVariable Integer id) {
        try {
            Medicamento medicamento = medicamentoService.getMedicamentoById(id);
            if (medicamento != null) {
                model.addAttribute("medicamento", medicamento);
            } else {
                model.addAttribute("medicamentoNoEncontrado", true);
            }
        } catch (Exception e) {
            model.addAttribute("medicamentoNoEncontrado", true);
        }
        return "medicamento/actualizar";
    }

    @PostMapping("/medicamento/actualizar/{id}")
    public String actualizarMedicamento(Model model, @PathVariable Integer id, @ModelAttribute Medicamento medicamento) {
        try {
            String result = medicamentoService.actualizarMedicamento(
                id,
                medicamento.getNombre(),
                medicamento.getDosis(),
                medicamento.getCantidad(),
                medicamento.getPrecio()
            );
            model.addAttribute("resultado", result);
        } catch (Exception e) {
            model.addAttribute("error", true);
        }
        return "redirect:/medicamentos";
    }

    @GetMapping("/medicamento/eliminar/{id}")
    public String eliminarMedicamento(Model model, @PathVariable Integer id) {
        try {
            String result = medicamentoService.eliminarMedicamento(id);
            model.addAttribute("resultado", result);
        } catch (Exception e) {
            model.addAttribute("error", true);
        }
        return "redirect:/medicamentos";
    }
}
