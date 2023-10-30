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


    private Long userId;

    private String userNombre;

    private String userApellido;
    
    private String userCorreo;

    private Integer userEdad;

    private String userSexo;
    
    private String userTipo;


}
