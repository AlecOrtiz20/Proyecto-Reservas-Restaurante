package com.example.reservas.restaurante.SistemaReservasRestaurante.Models;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Person {
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String city;
    private String country;

}
