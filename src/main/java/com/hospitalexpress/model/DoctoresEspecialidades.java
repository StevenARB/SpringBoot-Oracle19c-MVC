package com.hospitalexpress.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "doctores_especialidades")
//@IdClass(DoctorEspecialidadId.class)
@Getter
@Setter
public class DoctoresEspecialidades {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;
}