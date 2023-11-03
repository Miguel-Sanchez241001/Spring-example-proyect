package com.inventor.app.repository;

import com.inventor.app.model.Cita;
import com.inventor.app.model.Doctor;
import com.inventor.app.model.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepo extends CrudRepository<Cita,Long> {

    List<Cita> findCitasByPaciente(Paciente paciente);
    List<Cita> findCitasByDoctor(Doctor doctor);



}
