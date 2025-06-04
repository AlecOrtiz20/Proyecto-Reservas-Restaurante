package com.example.reservas.restaurante.SistemaReservasRestaurante.Models;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.RolesUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "adminId")
    private Admin admin;

    private String email;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private RolesUser rolesUser;

    @Temporal(TemporalType.DATE)
    private Date creatonDate;

}
