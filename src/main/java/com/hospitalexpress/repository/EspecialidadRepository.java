package com.hospitalexpress.repository;

import com.hospitalexpress.model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

    @Procedure(name = "Especialidad.getEspecialidadById")
    Map<String, Object> getEspecialidadById(@Param("p_id_especialidad") Integer id_especialidad);
}
