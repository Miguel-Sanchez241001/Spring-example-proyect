package com.inventor.app.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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


@Entity
public class Usuario {

 @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;

    private String userNombre;

    private String userApellido;
    
    private String userCorreo;

    private Integer userEdad;

    private String userSexo;
    
    private String userTipo;


}
