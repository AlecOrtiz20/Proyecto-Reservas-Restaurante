package com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Validation;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.ReservationStatus;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Impl.ValidationsImpl.ValidationStatusReservationImpl;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ValidationStatusReservation implements ValidationStatusReservationImpl {
    @Override
    public boolean validationStatus(Reservation reservation, ReservationStatus reservationStatus) {
        return reservationStatus != reservation.getReservationStatus();
    }
}
