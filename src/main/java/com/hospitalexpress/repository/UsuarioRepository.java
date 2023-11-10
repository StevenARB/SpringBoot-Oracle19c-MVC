/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.repository;

import com.hospitalexpress.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author retan
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Consultas personalizadas si es necesario
    @Procedure(name = "SP_INSERTAR_USUARIO")
    String insertarUsuario(
            @Param("p_username") String username,
            @Param("p_password") String password,
            @Param("p_rol") String rol,
            @Param("p_estado") String estado
    );
}
