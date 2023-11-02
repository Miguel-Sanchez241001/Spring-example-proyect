package com.inventor.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventor.app.model.Historia;

@Repository
public interface HistoriaRepo  extends CrudRepository<Historia, Long>{

    
} 
