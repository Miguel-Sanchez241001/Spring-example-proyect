package com.inventor.app.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    

    @RequestMapping("/login")
    public String GoLogin() {

        return "login";
    }

    
    @RequestMapping("/")
    public String Goindex() {

        return "index";
    }
   @RequestMapping("/buscar")
    public String GoBuscar() {

        return "login";
    }
   @RequestMapping("/listar")
    public String Golistar() {

        return "login";
    }



   @RequestMapping("/otraPagina")
    public String otraPagina() {

        return "login";
    }

}
