package com.inventor.app.service;

import java.util.List;

import com.inventor.app.model.Historia;

public interface HistoriaService {


    Historia saveHistoria(Historia pd);

    List<Historia> getAllHistorias();

    Historia getHistoriabyId(Long id);


    Historia updateHistoria(Historia pd,Long id);
    
    void deleteDetail(Long id);

    
}
