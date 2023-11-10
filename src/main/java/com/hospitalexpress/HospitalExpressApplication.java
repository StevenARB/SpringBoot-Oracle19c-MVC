package com.hospitalexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hospitalexpress.service.EmpleadoService;
import com.hospitalexpress.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HospitalExpressApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalExpressApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner commandLineRunner(UsuarioService usuarioService) {
        return args -> {
            // Llama al método para imprimir empleados en la consola
            String username = "usuario1";
            String password = "123";
            String rol = "Cliente";
            String estado = "Activio";
            usuarioService.insertarUsuario(username, password, rol, estado);
        };
    }*/

}
