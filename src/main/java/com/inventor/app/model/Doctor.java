package com.inventor.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor


@Entity
public class Doctor {
    
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
    private Long docId;
    
    @OneToOne
    private Usuario docUsuario;

    private String  docEspecialidad;
    
    private String docnumeroTelefono;

    private String docConsultorio;
}
