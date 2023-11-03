package com.inventor.app.service;

import com.inventor.app.model.Cita;
import com.inventor.app.model.Doctor;
import com.inventor.app.model.Paciente;

import java.util.List;

public interface CitaService {


    public List<Cita> ObtenerCitas();
    public  List<Cita> ObtenerCitasPaciente(Paciente paciente);


    public List<Cita> ObtenerCitasDocto(Doctor doctor);

   public void EliminarCita(Cita cita);

    public void NuevaCita(Cita cita);

    public Cita buscarCita(Long id);

    public void cambiarEstadoCita(Cita cita);




}
