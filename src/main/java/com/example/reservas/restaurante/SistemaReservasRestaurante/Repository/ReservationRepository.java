package com.example.reservas.restaurante.SistemaReservasRestaurante.Repository;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Client;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Page<Reservation> findByClientId(Client client, Pageable pageable);
}
