package com.example.reservas.restaurante.SistemaReservasRestaurante.Models;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.AdminStatus;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.RolesUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter
@Getter
public class Admin extends Person{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Enumerated(EnumType.STRING)
    private AdminStatus adminStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @Temporal(TemporalType.DATE)
    private Date creationDate;
}
