package com.example.reservas.restaurante.SistemaReservasRestaurante.DTo;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.RolesUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsDTO {

    private String username;
    private String email;
    private String password;
    private RolesUser rolesUser;
}
