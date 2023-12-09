package com.hospitalexpress.repository;

import com.hospitalexpress.model.Tratamiento;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    @Procedure(name = "Tratamiento.insertarTratamiento")
    String insertarTratamiento(
            @Param("p_nombre") String nombre,
            @Param("p_descripcion") String descripcion
    );

    @Procedure(name = "Tratamiento.getTratamientoById")
    Map<String, Object> getTratamientoById(@Param("p_id_tratamiento") Integer id_tratamiento);

    @Procedure(name = "Tratamiento.getTratamientos")
    List<Object[]> getTratamientos();

    @Procedure(name = "Tratamiento.actualizarTratamiento")
    String actualizarTratamiento(
            @Param("p_id_tratamiento") Integer id,
            @Param("p_nombre") String nombre,
            @Param("p_descripcion") String descripcion
    );

    @Procedure(name = "Tratamiento.eliminarTratamiento")
    String eliminarTratamiento(@Param("p_id_tratamiento") Integer id);

}
