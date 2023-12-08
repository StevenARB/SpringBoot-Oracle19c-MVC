/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.service;

import com.hospitalexpress.model.Usuario;
import com.hospitalexpress.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author retan
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public String insertarUsuario(String username, String password, String rol, String estado) {
        try {
            String result = usuarioRepository.insertarUsuario(username, password, rol, estado);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        try {
            List<Object[]> resultList = usuarioRepository.getUsuarios();
            List<Usuario> usuarios = new ArrayList<>();

            for (Object[] result : resultList) {
                BigDecimal id = (BigDecimal) result[0];
                String username = (String) result[1];
                String password = (String) result[2];
                String rol = (String) result[3];
                String estado = (String) result[4];

                Usuario usuario = new Usuario();
                usuario.setId(id.intValue());
                usuario.setUsername(username);
                usuario.setPassword(password);
                usuario.setRol(rol);
                usuario.setEstado(estado);

                usuarios.add(usuario);
            }

            if (!usuarios.isEmpty()) {
                return usuarios;
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    public Usuario getUsuarioById(Integer id) {
        try {

            Map<String, Object> result = usuarioRepository.getUsuarioById(id);

            if (result.get("p_resultado").equals("EXITO")) {
                Usuario usuario = new Usuario();
                usuario.setId((Integer) id);
                usuario.setUsername((String) result.get("p_username"));
                usuario.setPassword((String) result.get("p_password"));
                usuario.setRol((String) result.get("p_rol"));
                usuario.setEstado((String) result.get("p_estado"));

                return usuario;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Usuario getUsuarioByUsername(String username) {
        try {

            Map<String, Object> result = usuarioRepository.getUsuarioByUsername(username);

            if (result.get("p_resultado").equals("EXITO")) {
                Usuario usuario = new Usuario();
                usuario.setUsername((String) username);
                usuario.setId((Integer) result.get("p_id_usuario"));
                usuario.setRol((String) result.get("p_rol"));
                usuario.setEstado((String) result.get("p_estado"));

                return usuario;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public String actualizarUsuario(Integer id, 
            String username, String password, String rol, String estado) {
        try {
            String result = usuarioRepository.actualizarUsuario(id, username, password, rol, estado);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public String eliminarUsuario(String username) {
        try {
            String result = usuarioRepository.eliminarUsuario(username);
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
