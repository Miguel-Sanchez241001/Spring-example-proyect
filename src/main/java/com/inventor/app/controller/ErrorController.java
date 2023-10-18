package com.inventor.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    

 @RequestMapping("/error")
    public String handleError(HttpServletRequest request,Model model) {
      Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    
    if (status != null) {
        Integer statusCode = Integer.valueOf(status.toString());
    
        if(statusCode == HttpStatus.NOT_FOUND.value()) {
           model.addAttribute("message", "No encontrado");
        }
        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
           model.addAttribute("message", "Error interno en el servidor");
        }
         else if(statusCode == HttpStatus.FORBIDDEN.value()) {
           model.addAttribute("message", "Sin permisos ");
        }


    }       
     return "error";
    }



}
