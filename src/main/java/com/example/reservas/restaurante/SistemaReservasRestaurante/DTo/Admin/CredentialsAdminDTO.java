package com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Admin;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.RolesUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CredentialsAdminDTO {
    @NotBlank(message = "Username no puede estar vacío")
    @Size(min = 5, max = 20, message = "Username debe tener entre 5 y 20 caracteres")
    private String username;

    @NotBlank(message = "Email no puede estar vacío")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 12, message = "La contraseña debe tener al menos 12 caracteres")
    private String password;

    @NotNull(message = "El rol no puede ser nulo")
    private RolesUser rolesUser;


}
