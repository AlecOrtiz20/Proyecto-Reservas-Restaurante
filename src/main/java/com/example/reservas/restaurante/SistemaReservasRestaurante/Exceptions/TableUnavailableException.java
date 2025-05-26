package com.example.reservas.restaurante.SistemaReservasRestaurante.Exceptions;

public class TableUnavailableException extends RuntimeException{

    public TableUnavailableException(String message){
        super(message);
    }
}
