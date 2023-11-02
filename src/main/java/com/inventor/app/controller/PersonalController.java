package com.inventor.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/personal")
public class PersonalController {
    
    // @Autowired
    // private UsuarioServiceImpl UsuarioServiceImpl; 

    @RequestMapping("/nuevo")
    public String neuvoUsuario(){





  return "forms/usuario";
        
    }


    @RequestMapping("/prepararnuevo")
    public String  prepararnuevo(){
        return "forms/usuario";

    }
    

}
