package com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequestDTO {

    private ClientDTO clientDTO;
    private CredentialsClientDTO credentialsClientDTO;
}
