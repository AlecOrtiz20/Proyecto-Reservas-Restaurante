package com.example.reservas.restaurante.SistemaReservasRestaurante.Models;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.RolesUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "clientId")
    private Client idClient;
    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private RolesUser rolesUser;

    @Temporal(TemporalType.DATE)
    private Date creatioDate;

}
