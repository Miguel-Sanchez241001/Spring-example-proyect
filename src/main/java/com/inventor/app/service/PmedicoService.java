package com.inventor.app.service;

import java.util.List;

import com.inventor.app.model.Usuario;

public interface PmedicoService {
    
    Usuario saveUsuario(Usuario pd);

    List<Usuario> getAllUsuarios();

    Usuario getUsuariobyId(Long id);


    Usuario updateUsuario(Usuario pd,Long id);
    
    void deleteDetail(Long id);
}
