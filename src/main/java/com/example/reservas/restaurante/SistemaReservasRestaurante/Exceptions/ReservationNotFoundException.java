package com.example.reservas.restaurante.SistemaReservasRestaurante.Exceptions;

public class ReservationNotFoundException extends RuntimeException{

    public ReservationNotFoundException(String message){
        super(message);
    }

}
