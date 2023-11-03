package com.inventor.app.controller;

import com.inventor.app.model.Cita;
import com.inventor.app.model.Doctor;
import com.inventor.app.model.Paciente;
import com.inventor.app.model.Usuario;
import com.inventor.app.service.CitaService;
import com.inventor.app.service.DoctorService;
import com.inventor.app.service.PacienteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/paciente")
public class PacienteController {




    @Autowired
    private CitaService citaService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PacienteService pacienteService;
    @RequestMapping("/cita")
    public String VerCita(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       Paciente paciente =  pacienteService.buscarPacienteByUsuario(auth.getName());
        String path = "";
        List<Cita> citas = citaService.ObtenerCitasPaciente(paciente);
        List< Doctor > doctores = doctorService.getAllDoctors();

        model.addAttribute("citas", citas);
        model.addAttribute("doctores", doctores);

        model.addAttribute("nuevaCita", new Cita());
          return "forms/cita";
    }

    @RequestMapping("/historia")
    public void VerHistoria(){


    }
    @RequestMapping(value = "/citasave",method = RequestMethod.POST)
    public String SalvarCita(@ModelAttribute("nuevaCita") Cita cita, HttpServletRequest request, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Paciente paciente =  pacienteService.buscarPacienteByUsuario(auth.getName());
        cita.setPaciente( paciente );
        cita.setEstado("PENDIENTE");
        citaService.NuevaCita(cita);
        model.addAttribute("mensaje","Exito guardando Cita");

        List<Cita> citas = citaService.ObtenerCitasPaciente(paciente);
        List< Doctor > doctores = doctorService.getAllDoctors();

        model.addAttribute("citas", citas);
        model.addAttribute("doctores", doctores);




        model.addAttribute("nuevaCita", new Cita());

        return "forms/cita";


    }




}
