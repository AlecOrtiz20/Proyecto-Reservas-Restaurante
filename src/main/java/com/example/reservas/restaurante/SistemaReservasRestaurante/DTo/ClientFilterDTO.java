package com.example.reservas.restaurante.SistemaReservasRestaurante.DTo;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.ClientStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientFilterDTO {

    private String name;
    private ClientStatus clientStatus;
}
