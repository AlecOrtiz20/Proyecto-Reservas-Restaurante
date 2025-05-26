package com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Admin;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//DTO para que el administrador cambie el estado de la reservacion realizada por el cliente
public class ChangeStatusReservationDTO {
    private Long idReservation;
    private ReservationStatus reservationStatus;
}
