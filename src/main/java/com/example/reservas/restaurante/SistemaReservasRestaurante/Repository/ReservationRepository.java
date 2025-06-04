package com.example.reservas.restaurante.SistemaReservasRestaurante.Repository;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Client;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Page<Reservation> findByClientId(Client client, Pageable pageable);

    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE " +
            "r.clientId = :clientId AND " +
            "r.reservationStatus IN (com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.ReservationStatus.PENDING, " +
            "com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.ReservationStatus.CONFIRMED) AND " +
            "r.dateAndTime >= CURRENT_TIMESTAMP")
    boolean hasActiveReservation(@Param("clientId") Long clientId);

}
