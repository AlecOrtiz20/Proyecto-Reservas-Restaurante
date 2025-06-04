package com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Admin;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AdminDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String city;
    private String country;
    private Date dateOfBirth;
}
