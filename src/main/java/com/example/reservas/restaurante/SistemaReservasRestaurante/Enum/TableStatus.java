package com.example.reservas.restaurante.SistemaReservasRestaurante.Enum;

public enum TableStatus {
    AVAILABLE("Libre"),     // La mesa está libre
    OCCUPIED("Ocupada"),      // La mesa está ocupada por clientes
    RESERVED("Resevada"),      // La mesa ha sido reservada
    CLEANING("Limpieza"),      // La mesa está en proceso de limpieza
    OUT_OF_SERVICE("No disponible"); // La mesa no está disponible por algún problema

    private final String dysplayName;

    TableStatus(String dysplayName) {
        this.dysplayName = dysplayName;
    }

    public String getDysplayName() {
        return dysplayName;
    }
}
