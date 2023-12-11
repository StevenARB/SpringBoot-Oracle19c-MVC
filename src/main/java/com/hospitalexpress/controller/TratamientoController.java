
package com.hospitalexpress.controller;

import com.hospitalexpress.model.Tratamiento;
import com.hospitalexpress.service.TratamientoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TratamientoController {
    
    @Autowired
private TratamientoService tratamientoService;

@GetMapping("/tratamiento/insertar")
public String insertarTratamiento(Model model) {
    model.addAttribute("tratamiento", new Tratamiento());
    return "tratamiento/insertar";
}

@PostMapping("/tratamiento/insertar")
public String insertarTratamiento(Model model, @ModelAttribute Tratamiento tratamiento) {
    try {
        String result = tratamientoService.insertarTratamiento(
                tratamiento.getNombre(),
                tratamiento.getDescripcion()
        );
        model.addAttribute("resultado", result);
    } catch (Exception e) {
        model.addAttribute("error", true);
    }
    return "tratamiento/insertar";
}

@GetMapping("/findTratamientoById/{id_tratamiento}")
public String findTratamientoById(Model model, @PathVariable Integer id_tratamiento) {
    try {
        if (tratamientoService.getTratamientoById(id_tratamiento) != null) {
            Tratamiento tratamiento = tratamientoService.getTratamientoById(id_tratamiento);
            System.out.println(tratamiento);
            model.addAttribute("tratamiento", tratamiento);
        } else {
            model.addAttribute("tratamientoNoEncontrado", true);
        }
    } catch (Exception e) {
        model.addAttribute("tratamientoNoEncontrado", true);
    }
    return "tratamiento/tratamiento";
}

@GetMapping("/tratamientos")
public String findTratamientos(Model model) {
    try {
        List<Tratamiento> listTratamientos = tratamientoService.getTratamientos();
        if (listTratamientos != null) {
            model.addAttribute("tratamientos", listTratamientos);
        } else {
            model.addAttribute("listaVacia", true);
        }
    } catch (Exception e) {
        model.addAttribute("listaVacia", true);
    }
    return "tratamiento/tratamientos";
}

@GetMapping("/tratamiento/actualizar/{id}")
public String findTratamientoByIdToUpdate(Model model, @PathVariable Integer id) {
    try {
        Tratamiento tratamiento = tratamientoService.getTratamientoById(id);
        if (tratamiento != null) {
            model.addAttribute("tratamiento", tratamiento);
        } else {
            model.addAttribute("tratamientoNoEncontrado", true);
        }
    } catch (Exception e) {
        model.addAttribute("tratamientoNoEncontrado", true);
    }
    return "tratamiento/actualizar";
}

@PostMapping("/tratamiento/actualizar/{id}")
public String actualizarTratamiento(Model model, @PathVariable Integer id, @ModelAttribute Tratamiento tratamiento) {
    try {
        String result = tratamientoService.actualizarTratamiento(id, tratamiento.getNombre(), tratamiento.getDescripcion());
        model.addAttribute("resultado", result);
    } catch (Exception e) {
        model.addAttribute("error", true);
    }
    return "redirect:/tratamientos";
}

@GetMapping("/tratamiento/eliminar/{id}")
public String eliminarTratamiento(Model model, @PathVariable Integer id) {
    try {
        String result = tratamientoService.eliminarTratamiento(id);
        model.addAttribute("resultado", result);
    } catch (Exception e) {
        model.addAttribute("error", true);
    }
    return "redirect:/tratamientos";
}


}
