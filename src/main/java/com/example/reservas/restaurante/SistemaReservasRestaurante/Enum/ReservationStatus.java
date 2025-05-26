package com.example.reservas.restaurante.SistemaReservasRestaurante.Enum;

public enum ReservationStatus {

    PENDING("Pendiente"),       // Reserva creada pero aún no confirmada
    CONFIRMED("Confirmada"),     // Confirmada por el restaurante
    CANCELLED("Cancelada"),     // Cancelada por el cliente o el sistema
    COMPLETED("Completada"),     // Reserva realizada exitosamente
    NO_SHOW("No presentado");        // El cliente no se presentó ; ;

    private final String displayName;


    ReservationStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}


