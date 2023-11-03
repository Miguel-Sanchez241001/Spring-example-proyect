package com.inventor.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaHoraHistoriaAtencion;


    private String pertenenciaEtnicaHist;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaHoraHistoriaActual;

    private String LnacimientoHit;


    private String gradoDeInstruccion;
    private String grupoSanguineo;

    private boolean factorRh;

    private String dniFamiliar;


    private String histNotas;


    

    
}
