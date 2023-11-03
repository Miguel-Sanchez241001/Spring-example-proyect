package com.inventor.app.config.securityauth;

import com.inventor.app.config.Credenciales;
import com.inventor.app.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


  
   @AllArgsConstructor
   public class SecurityUser implements UserDetails {
  
       private final Credenciales credenciales;
       private final Usuario usuario;
  
  
  
       @Override
       public Collection<? extends GrantedAuthority> getAuthorities() {
           List<GrantedAuthority> authorities = new ArrayList<>();
           authorities.add(new SimpleGrantedAuthority("ROLE_"+usuario.getUserTipo()));
           return authorities;
  
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


