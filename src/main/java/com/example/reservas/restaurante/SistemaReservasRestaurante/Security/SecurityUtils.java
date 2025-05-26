package com.example.reservas.restaurante.SistemaReservasRestaurante.Security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public String getCurrentUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()){
            throw new RuntimeException("El cliente no esta autenticado");
        }

        return authentication.getName();
    }
}
