package com.inventor.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.inventor.app.model.Usuario;

public interface UsuarioRepo extends CrudRepository<Usuario, Long>{
    
}
