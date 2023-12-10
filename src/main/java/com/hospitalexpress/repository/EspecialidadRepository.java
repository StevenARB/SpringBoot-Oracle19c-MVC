package com.hospitalexpress.repository;

import com.hospitalexpress.model.Especialidad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

    @Procedure(name = "Especialidad.insertarEspecialidad")
    String insertarEspecialidad(
            @Param("p_nombre") String nombre,
            @Param("p_descripcion") String descripcion
    );

    @Procedure(name = "Especialidad.getEspecialidadById")
    Map<String, Object> getEspecialidadById(@Param("p_id_especialidad") Integer id_especialidad);

    @Procedure(name = "Especialidad.getEspecialidades")
    List<Object[]> getEspecialidades();

    @Procedure(name = "Especialidad.actualizarEspecialidad")
    String actualizarEspecialidad(
            @Param("p_id_especialidad") Integer id,
            @Param("p_nombre") String nombre,
            @Param("p_descripcion") String descripcion
    );

    @Procedure(name = "Especialidad.eliminarEspecialidad")
    String eliminarEspecialidad(@Param("p_id_especialidad") Integer id);

}
