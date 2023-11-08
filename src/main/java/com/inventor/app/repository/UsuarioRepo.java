package com.inventor.app.repository;

import java.util.List;
import java.util.Optional;

import com.inventor.app.config.Credenciales;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventor.app.model.Usuario;

@Repository
public interface UsuarioRepo extends CrudRepository<Usuario, Long>{

    Optional<Usuario> findByUserNombre(String userNombre);
    Optional<Usuario> findByCredenciales(Credenciales credenciales);
    List<Usuario> findByUserTipo(String userTipo);
    Optional<Usuario>  findByUserCorreo(String userCorreo);

}
