package com.inventor.app.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {


    private String nombre;

    private String apellido;

    
    private String correo;

    private String password;


    private Integer edad;

   
    private Boolean sexo;

}
