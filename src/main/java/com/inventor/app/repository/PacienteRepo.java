package com.inventor.app.repository;

import com.inventor.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventor.app.model.Paciente;

import java.util.Optional;

@Repository
public interface PacienteRepo  extends CrudRepository<Paciente, Long>{

    Optional<Paciente> findByPacUsuario(Usuario usuario);
}
