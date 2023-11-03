package com.inventor.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.inventor.app.config.Credenciales;
import com.inventor.app.model.Usuario;

public interface PmedicoService {
    
    Usuario saveUsuario(Usuario pd,Credenciales cre);

    List<Usuario> getAllUsuarios();

    Optional<Usuario> getUsuariobyId(Long id);


    Usuario updateUsuario(Usuario pd,Long id);
    
    void deleteDetail(Long id);




    
}
