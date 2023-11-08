package com.inventor.app.service;

import java.util.List;
import java.util.Optional;

import com.inventor.app.model.Historia;
import com.inventor.app.model.Paciente;

public interface HistoriaService {


    Historia saveHistoria(Historia pd);

    List<Historia> getAllHistorias();

    Optional<Historia> getHistoriabyId(Long id);


    Historia updateHistoria(Historia pd,Long id);
    
    void deleteDetail(Long id);
    Optional<Historia> buscarHistoria(Paciente paciente);

    
}
