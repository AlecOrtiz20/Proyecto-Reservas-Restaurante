package com.example.reservas.restaurante.SistemaReservasRestaurante.Enum;

public enum ClientStatus {
    ACTIVE("Activo"),
    BLOCKED("Bloqueado");


    private final String displayName;
    ClientStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return this.displayName;
    }
}
