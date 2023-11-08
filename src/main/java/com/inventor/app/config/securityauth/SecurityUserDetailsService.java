package com.inventor.app.config.securityauth;

import com.inventor.app.config.Credenciales;
import com.inventor.app.model.Usuario;
import com.inventor.app.repository.CredencialesRepo;
import com.inventor.app.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityUserDetailsService  implements UserDetailsService {

@Autowired
private CredencialesRepo credencialesRepo;
@Autowired
private UsuarioRepo usuarioRepo;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

Optional<Credenciales> user = credencialesRepo.findByCreUsername(username);
if(user.isPresent()){
Optional<Usuario> usuario = usuarioRepo.findByCredenciales(user.get());

        //return new SecurityUser(user.get(),usuario.get());


    return org.springframework.security.core.userdetails.User
            .withUsername(username)
            .roles(usuario.get().getUserTipo().toUpperCase())
            .password(user.get().getCrePassword())
            .build();
}

throw new UsernameNotFoundException("User not found: " + username);
}
}

