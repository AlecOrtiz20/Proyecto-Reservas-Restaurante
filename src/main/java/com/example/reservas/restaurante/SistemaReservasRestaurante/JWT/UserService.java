package com.example.reservas.restaurante.SistemaReservasRestaurante.JWT;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Credentials;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Repository.CredentialsRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final CredentialsRepository credentialsRepository;

    public UserService(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("Nombre de usuario");
        System.out.println(username);

        Credentials client = this.credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(client.getUsername())
                .password(client.getPassword())
                .roles(client.getRolesUser().name())
                .build();

    }
}





