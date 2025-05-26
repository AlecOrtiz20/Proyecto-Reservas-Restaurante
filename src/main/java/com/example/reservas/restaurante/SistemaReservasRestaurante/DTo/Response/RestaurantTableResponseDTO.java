package com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Response;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.TableStatus;

public class RestaurantTableResponseDTO {

    private Long id;
    private Integer capacity;
    private String location;
    private TableStatus tableStatus;
    private String clientName;
}
