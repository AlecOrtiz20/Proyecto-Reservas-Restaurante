package com.example.reservas.restaurante.SistemaReservasRestaurante.Repository;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.AdminCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminCredentialsRepository  extends JpaRepository<AdminCredentials, Long> {
    Optional<AdminCredentials> findByUsername(String username);
}
