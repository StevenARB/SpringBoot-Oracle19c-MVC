/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.service;

import com.hospitalexpress.model.Usuario;
import com.hospitalexpress.repository.UsuarioRepository;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 * @author retan
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String insertarUsuario(String username, String password, String rol, String estado) {
        try {
            return usuarioRepository.insertarUsuario(username, password, rol, estado);
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }
}
