package com.inventor.app.controller;

import com.inventor.app.model.Cita;
import com.inventor.app.model.Doctor;
import com.inventor.app.model.Historia;
import com.inventor.app.model.Paciente;
import com.inventor.app.model.Usuario;
import com.inventor.app.repository.CredencialesRepo;
import com.inventor.app.repository.UsuarioRepo;
import com.inventor.app.service.CitaService;
import com.inventor.app.service.DoctorService;
import com.inventor.app.service.HistoriaService;
import com.inventor.app.service.PacienteService;
import com.inventor.app.service.impl.PacienteServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

  @Autowired
    private HistoriaService historiaService;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private CredencialesRepo credencialesRepo;

    @Autowired
    private CitaService citaService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PacienteService pacienteService;
      @Autowired
    private PacienteServiceImpl pacienteServiceImpl;
    @RequestMapping("/cita")
    public String VerCita(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       Paciente paciente =  pacienteService.buscarPacienteByUsuario(auth.getName());
        List<Cita> citas = citaService.ObtenerCitasPaciente(paciente);
        List< Doctor > doctores = doctorService.getAllDoctors();

        model.addAttribute("citas", citas);
        model.addAttribute("doctores", doctores);

        model.addAttribute("nuevaCita", new Cita());
          return "forms/cita";
    }

    @RequestMapping("/historia")
    public String VerHistoria(Model model){
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       

        Paciente paciente = pacienteServiceImpl.buscarPacienteByUsuario(auth.getName());
        Historia historianueva = historiaService.buscarHistoria(paciente).get();
        model.addAttribute("historianueva", historianueva);
        model.addAttribute("formpacienteper", true);
        return "forms/historia";

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
   
    @GetMapping(value = "/")
    public String endPointPublico(HttpServletRequest request, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String msg = "Estás accediendo al end point sin realizar una autentificación ya que es un end point publico";
        if (auth.isAuthenticated()) {

            Usuario usuario = usuarioRepo.findByCredenciales( credencialesRepo.findByCreUsername(auth.getName()).get()).get();
            msg = "Bienvenido " + usuario.getUserNombre() ;
        }
        model.addAttribute("message", msg);

        return "consultas/reporte";
    }



}
