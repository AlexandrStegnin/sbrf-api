package com.stegnin.sbrf.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Alexandr Stegnin
 */

@Service
public class AuthService {

    public String getWelcomeMessage() {
        return String.format("Hello, %s", getName());
    }

    public String getAuthInfo() {
        return String.format("Hello, %s! Your roles = %s", getName(), getRoles());
    }

    private String getName() {
        Authentication auth = getAuth();
        String name = "Guest";
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            name = auth.getName();
        }
        return name;
    }

    private String getRoles() {
        Authentication auth = getAuth();
        String roles = "";
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            roles = auth.getAuthorities().toString();
        }
        return roles;
    }

    private Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
