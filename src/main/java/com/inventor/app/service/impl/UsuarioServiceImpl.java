package com.inventor.app.service.impl;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.inventor.app.config.Credenciales;
import com.inventor.app.model.Usuario;
import com.inventor.app.repository.CredencialesRepo;
import com.inventor.app.repository.UsuarioRepo;
import com.inventor.app.service.PmedicoService;

@Service
public class UsuarioServiceImpl implements PmedicoService {

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private CredencialesRepo credencialesRepo;

    @Override
    @Transactional
    public Usuario saveUsuario(Usuario pd,Credenciales cre) {
        credencialesRepo.save(cre);

        return usuarioRepo.save(pd);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
               return (List<Usuario>) usuarioRepo.findAll();

    }

    @Override
    public Optional<Usuario> getUsuariobyId(Long id) {
        return usuarioRepo.findById(id);
    }

    @Override
    public Usuario updateUsuario(Usuario pd, Long id) {
        return usuarioRepo.save(pd);
    }

    @Override
    public void deleteDetail(Long id) {
        usuarioRepo.deleteById(id);
    }



    // @Override
    // public UserDetails cargarUsuarioForLogin(String username) {
    //     //获取用户信息
    //     Usuario usuario = usuarioRepo.findByUserNombre(username).get();

      
    //     if (usuario != null) {
    //         return new CredencialesDetail(usuario);
    //     }

    //     throw new UsernameNotFoundException("Error buscando");
    // }
    }
    

