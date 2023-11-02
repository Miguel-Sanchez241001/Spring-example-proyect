package com.inventor.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventor.app.model.Historia;
import com.inventor.app.repository.HistoriaRepo;
import com.inventor.app.service.HistoriaService;

@Service
public class HistoriaServiceImpl implements HistoriaService{


    @Autowired
    private HistoriaRepo historiaRepo;
    @Override
    public Historia saveHistoria(Historia pd) {
        return historiaRepo.save(pd);

    }

    @Override
    public List<Historia> getAllHistorias() {
        return (List<Historia>) historiaRepo.findAll();
    }

    @Override
    public Optional<Historia> getHistoriabyId(Long id) {
         return historiaRepo.findById(id);

    }

    @Override
    public Historia updateHistoria(Historia pd, Long id) {
        return historiaRepo.save(pd);

    }

    @Override
    public void deleteDetail(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteDetail'");
    }
    
}
