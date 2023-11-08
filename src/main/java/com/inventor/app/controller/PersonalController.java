package com.inventor.app.controller;

import com.inventor.app.model.Cita;
import com.inventor.app.repository.CredencialesRepo;
import com.inventor.app.repository.UsuarioRepo;
import com.inventor.app.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inventor.app.config.Credenciales;
import com.inventor.app.model.Doctor;
import com.inventor.app.model.Paciente;
import com.inventor.app.model.Usuario;
import com.inventor.app.service.impl.DoctorServiceImpl;
import com.inventor.app.service.impl.PacienteServiceImpl;
import com.inventor.app.service.impl.UsuarioServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@Controller()
@RequestMapping("/personal")
public class PersonalController {
    
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl; 

    @Autowired
    private PacienteServiceImpl pacienteServiceImpl; 
    
    @Autowired
    private DoctorServiceImpl doctorServiceImpl;

    @Autowired
    private CitaService citaService;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private CredencialesRepo credencialesRepo;
    @PostMapping("/nuevo")
    public String neuvoUsuario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request,Model model){
        Doctor doctor = new Doctor();   
           Paciente paciente = new Paciente();

          String usermane = usuario.getUserCorreo();
          String password = request.getParameter("password");
          Credenciales cre = new Credenciales(usermane,password);




        usuario.setCredenciales(cre);

        if(usuarioRepo.findByUserCorreo(usermane).isPresent()){
           model.addAttribute("usuario", usuario);
          request.setAttribute("tipo", "usuario"); 
          request.setAttribute("mensaje", "Correo ya existe");
          return "forms/usuario";
        }

        Usuario usuarioGuardaro = usuarioServiceImpl.saveUsuario(usuario, cre);
          request.setAttribute("mensaje", "Exito registrando usuario");  // objeto enviando 
          if (usuario.getUserTipo().equalsIgnoreCase("doctor")) {
              doctor.setDocUsuario(usuarioGuardaro); // objeto enviando

            model.addAttribute("doctor",doctor ); 
            request.setAttribute("tipo", "doctor");  // objeto enviando 


          }
          if(usuario.getUserTipo().equalsIgnoreCase("paciente")){
            paciente.setPacUsuario(usuarioGuardaro);
            model.addAttribute("paciente",paciente ); // objeto enviando
            
             request.setAttribute("tipo", "paciente");  // objeto enviando

          }







             model.addAttribute("idUser",usuarioGuardaro.getUserId() ); 

          //model.addAttribute("usuario", usuarioGuardaro);  // objeto enviando 

  return "forms/usuario";
        
    }


    @RequestMapping("/prepararnuevo")
    public String  prepararnuevo(Model model,HttpServletRequest request){
      Usuario usuarionew = new Usuario();
      request.setAttribute("tipo", "usuario");  // objeto enviando 

      model.addAttribute("usuario", usuarionew);


        return "forms/usuario";

    }

    @RequestMapping("/savedoctor")
    public String nuevoDoctor(@ModelAttribute("doctor") Doctor doctor, HttpServletRequest request,Model model){
              Long idUser = Long.parseLong(request.getParameter("iduser"));
          

          doctor.setDocUsuario(usuarioServiceImpl.getUsuariobyId(idUser).get());
        doctorServiceImpl.saveDoctor(doctor);

        request.setAttribute("mensaje", "Exito registrando doctor");  // objeto enviando 

        return "forms/usuario";

    }

      @RequestMapping("/savepaciente")
    public String nuevoPaciente(@ModelAttribute("paciente") Paciente paciente, HttpServletRequest request,Model model){
        String userid = request.getParameter("iduser");
        Long idUser = Long.parseLong(userid);
          

          paciente.setPacUsuario(usuarioServiceImpl.getUsuariobyId(idUser).get());

        pacienteServiceImpl.savePaciente(paciente);
       request.setAttribute("mensaje", "Exito registrando paciente");  // objeto enviando 

        return "forms/usuario";

    }



    @RequestMapping("/cita")
    public String VerCita(Model model){

       
        List<Cita> citas = citaService.ObtenerCitas();
        List< Doctor > doctores = doctorServiceImpl.getAllDoctors();

        model.addAttribute("citas", citas);
        model.addAttribute("doctores", doctores);


        // extra codigo no util para testeeo
        List<Paciente> pacientes = pacienteServiceImpl.getAllPacientes();

        model.addAttribute("pacientes", pacientes);




        model.addAttribute("nuevaCita", new Cita());



        return "forms/cita";
    }


    @RequestMapping("/actualizarestado")
    public String ActualizarEstado(Model model,HttpServletRequest request){

      
        Integer idCita = Integer.parseInt(request.getParameter("citaId"));
        String estado = request.getParameter("citaEstado");
        Cita cita = citaService.buscarCita(Long.valueOf(idCita));
        cita.setEstado(estado.toUpperCase());

        citaService.cambiarEstadoCita(cita);


        List<Cita> citas = citaService.ObtenerCitas();
        model.addAttribute("citas", citas);





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
