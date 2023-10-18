package com.inventor.app.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class ProjectSecurityUser {

    private String username;
    private String clave;
    private UserRole role;  
   




}
