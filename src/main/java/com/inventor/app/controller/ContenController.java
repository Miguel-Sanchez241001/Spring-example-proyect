package com.inventor.app.controller;

import com.inventor.app.model.Usuario;
import com.inventor.app.repository.CredencialesRepo;
import com.inventor.app.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ContenController {

	private static final Logger logger = LoggerFactory.getLogger(ContenController.class);


	@Autowired
	private UsuarioRepo usuarioRepo;
	@Autowired
	private CredencialesRepo credencialesRepo;
	// Ejemplo de end point PUBLICO
	@GetMapping(value = "/")
	public String endPointPublico(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String msg = "Estás accediendo al end point sin realizar una autentificación ya que es un end point publico";
		if (auth.isAuthenticated()) {

			Usuario usuario = usuarioRepo.findByCredenciales( credencialesRepo.findByCreUsername(auth.getName()).get()).get();
			msg = "Bienvenido" + usuario.getUserNombre() ;
		}
		model.addAttribute("message", msg);

		return "consultas/reporte";
	}

	// Ejemplo de end point PRIVADO
	@GetMapping(value = "/private")
	public String endPointPrivado(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("message",
				"Hola, tienes acceso a este recurso ya que " + "estás logeado con el user: " + auth.getName()
						+ " si no estubieras legeado, no podrías acceder aquí.");
		return "consultas/reporte";
	}

	// Ejemplo de End point con ROLE ADMIN
	@GetMapping(value = "/admin")
	public String HelloWorld(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mensaje = "";
		if (request.isUserInRole("ADMIN")) {
			mensaje = "El usuario " + auth.getName() + " tiene el rol de ADMIN por tanto puede acceder al recurso";
		} else {
			mensaje = "El usuario " + auth.getName()
					+ " no tiene el rol de ADMIN por tanto no puede acceder al recurso";
		}
		model.addAttribute("message", mensaje);
		return "consultas/reporte";
	}

	@RequestMapping("/index2")
	public String Goindex() {

		return "login";
	}

	@RequestMapping("/buscar")
	public String GoBuscar(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated()) {
			model.addAttribute("message", "fUNCION BUSCAR");

			return "consultas/reporte";
		}
		return "index";
	}

	@RequestMapping("/index")
	public String Golistar() {

		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
