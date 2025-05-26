package com.example.reservas.restaurante.SistemaReservasRestaurante.Models;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.TableStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TableStatus tableStatus;

    private Integer capacity;
    private String location;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

}
