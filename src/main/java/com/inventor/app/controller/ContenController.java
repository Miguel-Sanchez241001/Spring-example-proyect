package com.inventor.app.controller;
import org.springframework.security.core.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ContenController {
    

  
	private static final Logger logger = LoggerFactory.getLogger(ContenController.class);

	// Ejemplo de end point PUBLICO
	@GetMapping(value = "/public")
	public String endPointPublico(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String msg = "Estás accediendo al end point sin realizar una autentificación ya que es un end point publico";
		if (auth.isAuthenticated()) {
			msg = "Pese a no necesitarse ya que es un end point publico estás autentificado como " + auth.getName();
		}
model.addAttribute("message", msg);

		return "consultas/reporte";
	}

	// Ejemplo de end point PRIVADO
	@GetMapping(value = "/private")
	public String endPointPrivado(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("message", "Hola, tienes acceso a este recurso ya que " + "estás logeado con el user: " + auth.getName()
				+ " si no estubieras legeado, no podrías acceder aquí.");
		return "consultas/reporte";
	}

	// Ejemplo de End point con ROLE ADMIN
	@GetMapping(value = "/admin")
	public String HelloWorld(HttpServletRequest request,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (request.isUserInRole("ADMIN")) {
			logger.info("El usuario " + auth.getName() + " tiene el rol de ADMIN por tanto puede acceder al recurso");
		} else {
			logger.info("El usuario " + auth.getName() + " no tiene el rol de ADMIN por tanto no puede acceder al recurso");
		}
	model.addAttribute("message", "Puedes acceder a este recurso");
		return "consultas/reporte";
	}
    
    @RequestMapping("/")
    public String Goindex() {

        return "index";
    }
   @RequestMapping("/buscar")
    public String GoBuscar(Model model) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	model.addAttribute("message", "fUNCION BUSCAR");

        return "consultas/reporte";
    }
   @RequestMapping("/listar")
    public String Golistar(Model model) {
	model.addAttribute("message", "FUNCION LISTAR ");

        return "consultas/reporte";
    }




  

}
