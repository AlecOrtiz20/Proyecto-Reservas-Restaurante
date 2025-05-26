package com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Admin;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.TableStatus;
import lombok.Data;

@Data
public class AdminCreationTableDTO {

    private Long id;
    private Integer capacity;
    private TableStatus tableStatus;
    private String location;
}
