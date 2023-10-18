package com.inventor.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.inventor.app.model.Usuario;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    
    @RequestMapping("/loguear")
    public ModelAndView loguear(@Validated Usuario user, BindingResult result) {
        ModelAndView model = new ModelAndView();
      
        model.setViewName("login"); 
        return model;
    }
    @RequestMapping("/iniciar")
    public ModelAndView createUser( Usuario user, BindingResult result) {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            model.setViewName("consultas/reporte");
        } else {
                        model.setViewName("login");

        }
        return model;
    }


    @RequestMapping(value="/logout")
    public String logout() {
        
        return "index";
    }
    

}
