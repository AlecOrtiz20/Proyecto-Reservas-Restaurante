package com.example.reservas.restaurante.SistemaReservasRestaurante.Impl.ValidationsImpl;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.ReservationStatus;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Reservation;

public interface ValidationStatusReservationImpl {

    //Meotdo para validar que el estado no sea el mismo que ya esta puesto
    boolean validationStatus(Reservation reservation, ReservationStatus reservationStatus);
}
