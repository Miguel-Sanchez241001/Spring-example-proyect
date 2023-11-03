package com.inventor.app.config;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@Entity
public class Credenciales {
    
  

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idUserCre;

    private String creUsername;

    private String crePassword;

    public Credenciales(String usermane, String password) {

      creUsername = usermane;
      crePassword = password;
  }











}
