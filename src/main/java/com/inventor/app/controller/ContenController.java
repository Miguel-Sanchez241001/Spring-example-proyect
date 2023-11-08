package com.inventor.app.controller;

import com.inventor.app.model.Usuario;
import com.inventor.app.repository.CredencialesRepo;
import com.inventor.app.repository.UsuarioRepo;
import jakarta.servlet.http.HttpServletResponse;
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
	@RequestMapping("/")
	public String Golistar(HttpServletResponse response) {

		return "index";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
