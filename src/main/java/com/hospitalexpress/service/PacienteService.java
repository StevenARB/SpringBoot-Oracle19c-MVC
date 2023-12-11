/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalexpress.service;

import com.hospitalexpress.model.Paciente;
import com.hospitalexpress.model.Usuario;
import com.hospitalexpress.repository.PacienteRepository;
import com.hospitalexpress.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author retan
 */
@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public String insertarPaciente(String nombre, String primerApellido, String segundoApellido, String email, String direccion, String genero, String fechaNac) {
        try {
            String result = pacienteRepository.insertarPaciente(nombre, primerApellido, segundoApellido, email, direccion, genero, fechaNac);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Paciente> getPacientes() {
        try {
            List<Object[]> resultList = pacienteRepository.getPacientes();
            List<Paciente> pacientes = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (Object[] result : resultList) {
                BigDecimal id = (BigDecimal) result[0];
                String nombre = (String) result[1];
                String primerApellido = (String) result[2];
                String segundoApellido = (String) result[3];
                String email = (String) result[4];
                String direccion = (String) result[5];
                String genero = (String) result[6];
                String fechaNac = (String) dateFormat.format(result[7]);
                BigDecimal idUsuario = (BigDecimal) result[8];

                Paciente paciente = new Paciente();
                paciente.setId(id.intValue());
                paciente.setNombre(nombre);
                paciente.setPrimerApellido(primerApellido);
                paciente.setSegundoApellido(segundoApellido);
                paciente.setEmail(email);
                paciente.setDireccion(direccion);
                paciente.setGenero(genero);
                paciente.setFechaNac(fechaNac);

                if (idUsuario != null) {
                    Map<String, Object> resultUsuario = usuarioRepository.getUsuarioByEmail(email);
                    Usuario usuario = new Usuario();
                    usuario.setId((Integer) resultUsuario.get("p_id_usuario"));
                    usuario.setEmail((String) email);
                    usuario.setPassword((String) resultUsuario.get("p_password"));
                    usuario.setRol((String) resultUsuario.get("p_rol"));
                    usuario.setEstado((String) resultUsuario.get("p_estado"));
                    paciente.setUsuario(usuario);
                }

                pacientes.add(paciente);
            }

            if (!pacientes.isEmpty()) {
                return pacientes;
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    public Paciente getPacienteById(Integer id) {
        try {

            Map<String, Object> result = pacienteRepository.getPacienteById(id);
            Map<String, Object> resultUsuario = usuarioRepository.getUsuarioByEmail((String) result.get("p_email"));

            SimpleDateFormat formatoOriginal = new SimpleDateFormat("dd/MM/yy");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            if (result.get("p_resultado").equals("EXITO")) {
                Paciente paciente = new Paciente();
                paciente.setId((Integer) id);
                paciente.setNombre((String) result.get("p_nombre"));
                paciente.setPrimerApellido((String) result.get("p_primer_apellido"));
                paciente.setSegundoApellido((String) result.get("p_segundo_apellido"));
                paciente.setEmail((String) result.get("p_email"));
                paciente.setDireccion((String) result.get("p_direccion"));
                paciente.setGenero((String) result.get("p_genero"));

                paciente.setFechaNac((String) dateFormat.format(formatoOriginal.parse((String) result.get("p_fecha_nac"))));

                if (resultUsuario.get("p_resultado").equals("EXITO")) {
                    Usuario usuario = new Usuario();
                    usuario.setId((Integer) resultUsuario.get("p_id_usuario"));
                    usuario.setEmail((String) paciente.getEmail());
                    usuario.setPassword((String) resultUsuario.get("p_password"));
                    usuario.setRol((String) resultUsuario.get("p_rol"));
                    usuario.setEstado((String) resultUsuario.get("p_estado"));

                    paciente.setUsuario(usuario);
                }

                return paciente;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /*public Paciente getPacienteByEmail(String email) {
        try {

            Map<String, Object> result = pacienteRepository.getPacienteByEmail(email);

            if (result.get("p_resultado").equals("EXITO")) {
                Paciente paciente = new Paciente();
                paciente.setEmail((String) email);
                paciente.setId((Integer) result.get("p_id_paciente"));
                paciente.setRol((String) result.get("p_rol"));
                paciente.setEstado((String) result.get("p_estado"));

                return paciente;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }*/
    @Transactional
    public String actualizarPaciente(Integer id, String nombre, String primerApellido, String segundoApellido, String email, String direccion, String genero, String fechaNac) {
        try {
            String result = pacienteRepository.actualizarPaciente(id, nombre, primerApellido, segundoApellido, email, direccion, genero, fechaNac);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public String eliminarPaciente(String email) {
        try {
            String result = pacienteRepository.eliminarPaciente(email);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Integer getNumeroPacientes() {
        try {
            Integer numeroPacientes = pacienteRepository.getNumeroPacientes(null);
            if (numeroPacientes > 0) {
                return numeroPacientes;
            } else {
                return 0;
            }

        } catch (Exception e) {
            return 0;
        }
    }
}
