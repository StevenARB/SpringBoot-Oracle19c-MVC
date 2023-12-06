/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.repository;

import com.hospitalexpress.model.Usuario;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author retan
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    @Procedure(name = "Usuario.getUsuarios")
    List<Object[]> getUsuarios();

    @Procedure(name = "Usuario.getUsuarioByUsername")
    Map<String, Object> getUsuarioByUsername(@Param("p_username") String username);

}
