package com.example.reservas.restaurante.SistemaReservasRestaurante.Repository;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    public Optional<Credentials> findByUsername(String username);
}
