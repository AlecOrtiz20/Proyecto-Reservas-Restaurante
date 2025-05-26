package com.example.reservas.restaurante.SistemaReservasRestaurante.Models;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.ReservationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Client clientId;

    @ManyToOne
    private RestaurantTable restaurantTableId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAndTime;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @NotNull
    @Min(1)
    private Integer numberOfPeople;

    @Size(max = 500)
    private String comment;

    @Temporal(TemporalType.DATE)
    private Date creationDate;
}
