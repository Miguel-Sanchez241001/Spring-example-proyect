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
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idUserCre;
    
    @Column(unique = true)
    private String creUsername;

    private String crePassword;

    public Credenciales(String usermane, String password) {

      creUsername = usermane;
      crePassword = password;
  }











}
