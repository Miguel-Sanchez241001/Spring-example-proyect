package com.inventor.app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventor.app.config.Credenciales;

@Repository
public interface CredencialesRepo extends CrudRepository<Credenciales, Long>{


    Optional<Credenciales> findByCreUsername(String creUsername);
    
}
