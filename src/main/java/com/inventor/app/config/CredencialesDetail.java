package com.inventor.app.config;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.inventor.app.model.Usuario;

public class CredencialesDetail implements UserDetails {

    private Credenciales credenciales;
    private Usuario usuario;


    public CredencialesDetail(Usuario usuario) {
        this.usuario = usuario;
        credenciales = usuario.getCredenciales();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();
            collection.add(new SimpleGrantedAuthority(usuario.getUserTipo()));
        return collection;
    }

    @Override
    public String getPassword() {
       return credenciales.getCrePassword();
    }

    @Override
    public String getUsername() {
               return credenciales.getCreUsername();

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
            return true;

    }

    @Override
    public boolean isCredentialsNonExpired() {
               return true;

    }

    @Override
    public boolean isEnabled() {
           return true;

    }
    
}
