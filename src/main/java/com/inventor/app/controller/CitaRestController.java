package com.inventor.app.controller;


import com.inventor.app.model.Doctor;
import com.inventor.app.model.Horario;
import com.inventor.app.repository.DoctorRepo;
import com.inventor.app.repository.HorarioRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/cita")
public class CitaRestController {


    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private HorarioRepo horarioRepo;

    @GetMapping(value = "/especialidad/{especialidadName}" ) // , produces = MediaType.TEXT_EVENT_STREAM_VALUE trae problemas para manejar la data la convertiene toda a texto
    public Flux<Doctor> getDoctorsBySpecialty(@PathVariable String especialidadName) {
        Flux<Doctor> flux = Flux.fromIterable(doctorRepo.findByDocEspecialidad(especialidadName));
        return flux;
    }
    @GetMapping(value = "/horario/{doctor}/{fecha}" ) // , produces = MediaType.TEXT_EVENT_STREAM_VALUE trae problemas para manejar la data la convertiene toda a texto
    public Flux<Horario> getDoctoresHorario(@PathVariable Long doctor, @PathVariable String fecha) {
        Flux<Horario> flux = Flux.fromIterable(horarioRepo.obtenerHorariosDisponibles(doctor,fecha));

        return flux;
    }
}
