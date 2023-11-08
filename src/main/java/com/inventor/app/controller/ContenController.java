package com.inventor.app.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inventor.app.config.Credenciales;
import com.inventor.app.model.Horario;
import com.inventor.app.model.Usuario;
import com.inventor.app.repository.HorarioRepo;
import com.inventor.app.repository.UsuarioRepo;
import com.inventor.app.service.impl.UsuarioServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Controller
public class ContenController {
	@Autowired
	private HorarioRepo horarioRepository;

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Autowired
    private UsuarioServiceImpl usuarioServiceImpl; 
	@RequestMapping("/")
	public String GoInicio(Model model) {
		boolean adminExiste = usuarioRepo.findByUserTipo("PMEDICO").size()>=1;

		model.addAttribute("PERSONAL", adminExiste);
		return "index";
	}
	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equals("anonymousUser")) {
            new SecurityContextLogoutHandler().logout(request, response, auth); 
        }
		return "login";
	}

	@RequestMapping("/registro")
	public String registroNuevoAdmin(Model model,HttpServletRequest request) {
		Usuario usuarionew = new Usuario();
		request.setAttribute("tipo", "usuario");
		model.addAttribute("usuario", usuarionew);

		return "usueradmin";
	}
	 @PostMapping("/nuevo")
    public String neuvoUsuario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request,Model model){
    

          String usermane = usuario.getUserNombre() + usuario.getUserEdad();
          String password = request.getParameter("password");
          Credenciales cre = new Credenciales(usermane,password);


			RegistrarHorario();

        	usuario.setCredenciales(cre);
       		Usuario usuarioAdmin = usuarioServiceImpl.saveUsuario(usuario, cre);



			
          request.setAttribute("mensaje", "Exito registrando usuario personal");  // objeto enviando 
			request.setAttribute("username", "su usuario es "+usuarioAdmin.getCredenciales().getCreUsername());
			request.setAttribute("pass", "Su clave es es "+usuarioAdmin.getCredenciales().getCrePassword() );

  return "usueradmin";

}

public void RegistrarHorario(){
	  Map<Integer, String> horariosRamgo = new HashMap<>();
            int id = 0;
            for (int i = 1; i <= 8; i++) {
                if (i +7 == 12) continue;
                horariosRamgo.put(id++, String.format("%02d:00 - %02d:00", i +7 , i +7 + 1));
            }
            // Inserta 6 items generales en la tabla horario
            List<Horario> horarios = new ArrayList<>();
            Horario horario ;
            for (int i = 1; i <= 6; i++) {
                horario = new Horario();
                horario.setRango(horariosRamgo.get(i));

                horarios.add(horario);
            }
            horarioRepository.saveAll(horarios);

}
}
