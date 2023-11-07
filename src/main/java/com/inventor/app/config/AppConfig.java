package com.inventor.app.config;

import com.inventor.app.model.Horario;
import com.inventor.app.model.Paciente;
import com.inventor.app.model.Usuario;
import com.inventor.app.repository.CredencialesRepo;
import com.inventor.app.repository.HorarioRepo;
import com.inventor.app.repository.PacienteRepo;
import com.inventor.app.repository.UsuarioRepo;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration

@Transactional

public class AppConfig {


    /*@Bean
    public CommandLineRunner init(HorarioRepo horarioRepository, CredencialesRepo credencialesRepo, UsuarioRepo usuarioRepo, PacienteRepo pacienteRepo) {
        return args -> {

            Map<Integer, String> horariosRamgo = new HashMap<>();
            int id = 0;
            for (int i = 1; i <= 8; i++) {
                if (i +7 == 12) continue;
                horariosRamgo.put(id++, String.format("%02d:00 - %02d:00", i +7 , i +7 + 1));
            }
            // Inserta 6 items generales en la tabla horario
            List<Horario> horarios = new ArrayList<>();
            Horario horario ;
            for (int i = 1; i <= 6; i++) {
                horario = new Horario();
                horario.setRango(horariosRamgo.get(i));

                horarios.add(horario);
            }
            horarioRepository.saveAll(horarios);

            List<Credenciales> credenciales = new ArrayList<>();
            Credenciales credenciales1 = new Credenciales("admin", "admin");



            credenciales.add(credenciales1);

            credencialesRepo.saveAll(credenciales);

            List<Usuario> usuarios = new ArrayList<>();
            Usuario usuario = new Usuario();
            usuario.setCredenciales(credenciales1);
            usuario.setUserApellido("admin");
            usuario.setUserCorreo("admin@admin.com");
            usuario.setUserEdad(45);
            usuario.setUserNombre("admin");
            usuario.setUserSexo("Masculino");
            usuario.setUserTipo("PMEDICO");
            usuarios.add(usuario);
            usuarioRepo.saveAll(usuarios);




        };
    }*/
}
