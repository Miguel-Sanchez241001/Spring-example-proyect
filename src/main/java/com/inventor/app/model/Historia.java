package com.inventor.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Historia {
 @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idHist;
    @OneToOne
    private Paciente histPaciente;
    @OneToOne
    private Doctor histDoctor;
    private String histDiagnostico;
    private String histFechaCreacion;
    private String histNotas;
    

    
}
