package com.example.reservas.restaurante.SistemaReservasRestaurante.Enum;

public enum AdminStatus {
    ACTIVE("Activo"),
    PENDING_ACTIVATION("Pendiente de Activacion"),
    SUSPENDED("Suspendido"),
    INACTIVE("Inactivo");

    private String displayName;

    AdminStatus(String displayName) {
        this.displayName = displayName;
    }
}
