/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.service;

import com.hospitalexpress.model.Usuario;
import com.hospitalexpress.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 *
 * @author retan
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUsuarioByUsername(String username) {
        
        Map<String, Object> result = usuarioRepository.getUsuarioByUsername(username);

        try {
            
            if (result != null && !result.isEmpty()) {
                Usuario usuario = new Usuario();
                usuario.setUsername((String) username);
                usuario.setId((Integer) result.get("p_id_usuario"));
                usuario.setRol((String) result.get("p_rol"));
                usuario.setEstado((String) result.get("p_estado"));

                System.out.println(usuario.getId());

                return usuario;
            } else {
                return null;
            }
            
        } catch (Exception e) {
            return null;
        }

    }
}
