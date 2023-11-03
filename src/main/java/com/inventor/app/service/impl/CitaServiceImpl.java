package com.inventor.app.service.impl;

import com.inventor.app.model.Cita;
import com.inventor.app.model.Doctor;
import com.inventor.app.model.Paciente;
import com.inventor.app.repository.CitaRepo;
import com.inventor.app.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {


    @Autowired
    private CitaRepo citaRepo;

    @Override
    public List<Cita> ObtenerCitas() {


        return (List<Cita>) citaRepo.findAll();
    }

    @Override
    public List<Cita> ObtenerCitasPaciente(Paciente paciente) {
        return (List<Cita>) citaRepo.findCitasByPaciente(paciente);
    }

    @Override
    public List<Cita> ObtenerCitasDocto(Doctor doctor) {

        return (List<Cita>) citaRepo.findCitasByDoctor(doctor);

    }

    @Override
    public void EliminarCita(Cita cita) {
        citaRepo.delete(cita);

    }

    @Override
    public void NuevaCita(Cita cita) {
        citaRepo.save(cita);
    }

    @Override
    public Cita buscarCita(Long id) {
        return citaRepo.findById(id).get();
    }

    @Override
    public void cambiarEstadoCita(Cita cita) {
            citaRepo.save(cita);
    }
}
