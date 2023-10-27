package com.inventor.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // viene con una libreria JSON interna para mostrar data
public class ExtraResController {

    @Autowired
    public SessionRegistry sessionRegistry;

    @GetMapping(value = "/session")
    public ResponseEntity<?> getDetailSession() {
 List<String> sessionId = new ArrayList<String>();
 List<User> userObject = new ArrayList<User>();
        List<Object> sessiones = sessionRegistry.getAllPrincipals();
        for (Object object : sessiones) {
            if (object instanceof User) {
                userObject.add((User) object);
            }

            List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(object, false);

            for (SessionInformation sessionInformation : sessionInformations) {

                sessionId.add(sessionInformation.getSessionId());

            }

        }

        Map<String, Object> response = new HashMap<>();
        response.put("response", "Hola");
        response.put("SessionIds", sessionId);
        response.put("sessionuSERs", userObject);

        return ResponseEntity.ok(response);
    }

}
