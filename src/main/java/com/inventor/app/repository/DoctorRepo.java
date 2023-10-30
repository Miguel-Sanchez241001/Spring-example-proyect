package com.inventor.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventor.app.model.Doctor;

@Repository
public interface DoctorRepo  extends CrudRepository<Doctor, Long>{
    
}
