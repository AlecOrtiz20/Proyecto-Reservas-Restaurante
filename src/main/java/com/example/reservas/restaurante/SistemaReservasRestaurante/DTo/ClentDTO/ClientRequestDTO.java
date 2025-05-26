package com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO;


import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClientDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.CredentialsDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequestDTO {

    private ClientDTO clientDTO;
    private CredentialsDTO credentialsDTO;
}
