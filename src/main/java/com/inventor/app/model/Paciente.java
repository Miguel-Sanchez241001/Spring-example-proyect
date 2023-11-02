package com.inventor.app.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="paciente")
public class Paciente {

   @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
    private Long    pacId;
    private String  pacFechaNacimiento;
    private String  pacDireccion;
    private String  pacNumeroTelefono;
    @OneToOne
    private Usuario pacUsuario;
    @OneToOne
    private Historia pacHistorias; 
    
}
