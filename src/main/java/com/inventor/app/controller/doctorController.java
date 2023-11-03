package com.inventor.app.controller;

import com.inventor.app.model.Cita;
import com.inventor.app.model.Doctor;
import com.inventor.app.model.Historia;
import com.inventor.app.model.Paciente;
import com.inventor.app.service.CitaService;
import com.inventor.app.service.HistoriaService;
import com.inventor.app.service.impl.DoctorServiceImpl;
import com.inventor.app.service.impl.PacienteServiceImpl;
import com.inventor.app.service.impl.UsuarioServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller()
@RequestMapping("/doctor")
public class DoctorController {


    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private PacienteServiceImpl pacienteServiceImpl;

    @Autowired
    private DoctorServiceImpl doctorServiceImpl;

    @Autowired
    private CitaService citaService;
    @Autowired
    private HistoriaService historiaService;

    @RequestMapping("/historia")
    public String VerHistoriaClinica(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Doctor doctor =  doctorServiceImpl.buscarPacienteByUsuario(auth.getName());


        List<Cita> citas = citaService.ObtenerCitasDocto(doctor);

        model.addAttribute("citas", citas);


        Historia historianueva = new Historia();
        model.addAttribute("historianueva", historianueva);
        // extra codigo no util para testeeo

        return "forms/historia";
    }


    @RequestMapping("/actualizarhistoria")
    public String EditarHistoriaClinica(@ModelAttribute("historianueva") Historia historia, HttpServletRequest request,Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Doctor doctor =  doctorServiceImpl.buscarPacienteByUsuario(auth.getName());
        Integer idPaciente = Integer.parseInt(request.getParameter("paciente"));
              Paciente paciente = pacienteServiceImpl.getPacientebyId(Long.valueOf(idPaciente)).get();

              historia.setHistDoctor(doctor);
              historia.setHistPaciente(paciente);
              historiaService.saveHistoria(historia);

        request.setAttribute("mensaje", "Exito registrando usuario");
        return "forms/historia";

    }


    @RequestMapping("/cita")
    public String verCitasMedicas(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Doctor doctor =  doctorServiceImpl.buscarPacienteByUsuario(auth.getName());


        List<Cita> citas = citaService.ObtenerCitasDocto(doctor);
        List<Doctor> doctores = doctorServiceImpl.getAllDoctors();

        model.addAttribute("citas", citas);
        model.addAttribute("doctores", doctores);


        // extra codigo no util para testeeo
        List<Paciente> pacientes = pacienteServiceImpl.getAllPacientes();

        model.addAttribute("pacientes", pacientes);




        model.addAttribute("nuevaCita", new Cita());



        return "forms/cita";

    }


    @RequestMapping("/actualizarestado")
    public String ActualizarEstado(Model model, HttpServletRequest request){


        Integer idCita = Integer.parseInt(request.getParameter("citaId"));
        String estado = request.getParameter("citaEstado");
        Cita cita = citaService.buscarCita(Long.valueOf(idCita));
        cita.setEstado(estado.toUpperCase());

        citaService.cambiarEstadoCita(cita);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Doctor doctor =  doctorServiceImpl.buscarPacienteByUsuario(auth.getName());


        List<Cita> citas = citaService.ObtenerCitasDocto(doctor);
        model.addAttribute("citas", citas);
        model.addAttribute("mensaje","Listo Historia");
        return "forms/cita";
    }



    
}
