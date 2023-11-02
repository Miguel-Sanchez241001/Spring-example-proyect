package com.inventor.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller()
@RequestMapping("/personal")
public class PersonalController {
    
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl; 

    @Autowired
    private PacienteServiceImpl pacienteServiceImpl; 
    
    @Autowired
    private DoctorServiceImpl doctorServiceImpl; 

    @PostMapping("/nuevo")
    public String neuvoUsuario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request,Model model){
        Doctor doctor = new Doctor();   
           Paciente paciente = new Paciente();

          String usermane = usuario.getUserNombre() + usuario.getUserEdad();
          String password = request.getParameter("password");
          Credenciales cre = new Credenciales(usermane,password);
         usuario.setCredenciales(cre);
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
    
    

}
