package com.example.reservas.restaurante.SistemaReservasRestaurante.Service;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Credentials;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Repository.CredentialsRepository;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Security.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CurrentClientService {

    private final CredentialsRepository credentialsRepository;
    private final SecurityUtils securityUtils;

    public Credentials getCurrentUser(){

        String usernameClient = this.securityUtils.getCurrentUsername();

        return this.credentialsRepository.findByUsername(usernameClient)
                .orElseThrow(() -> new RuntimeException("El Cliente no existe"));

    }

}
