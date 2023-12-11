package com.hospitalexpress.controller;

import com.hospitalexpress.model.Cita;
import com.hospitalexpress.service.CitaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Cata
 */
@Controller
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping("/findCitaById/{id_cita}")
    public String findCitaById(Model model, @PathVariable Integer id_cita) {
        try {
            Cita cita = citaService.getCitaById(id_cita);
            if (cita != null) {
                model.addAttribute("cita", cita);
            } else {
                model.addAttribute("citaNoEncontrada", true);
            }
        } catch (Exception e) {
            model.addAttribute("citaNoEncontrada", true);
        }
        return "cita/citas";
    }

    @GetMapping("/citas")
    public String findCitas(Model model) {
        try {
            List<Cita> listCitas = citaService.getCitas();
            if (listCitas != null) {
                model.addAttribute("citas", listCitas);
            } else {
                model.addAttribute("listaVacia", true);
            }
        } catch (Exception e) {
            model.addAttribute("listaVacia", true);
        }
        return "cita/citas";
    }

    @GetMapping("/cita/insertar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cita", new Cita());

        return "cita/insertar";
    }

    /**
     * @PostMapping("/cita/insertar") public String insertarCita(@ModelAttribute
     * Cita cita, Model model) { citaService.insertarCita(cita.getId(),
     * cita.getTipo(), cita.getFecha_hora(), cita.getEstado());
     * model.addAttribute("mensaje", "Cita insertada exitosamente");
     *
     * return "cita/insertar";
    }*
     */
   /** @GetMapping("/cita/eliminar/{id}")
    public String eliminarCita(Model model, @PathVariable Integer id) {
        try {
            String result = citaService.eliminarCita(id);
            model.addAttribute("resultado", result);
        } catch (Exception e) {
            model.addAttribute("error", true);
        }
        return "redirect:/citas";
    }**/

}
