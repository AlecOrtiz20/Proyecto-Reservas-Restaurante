package com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.TableStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientTableRestaurantDTO {

    private Integer capacity;
    private TableStatus tableStatus;
    private String location;
}
