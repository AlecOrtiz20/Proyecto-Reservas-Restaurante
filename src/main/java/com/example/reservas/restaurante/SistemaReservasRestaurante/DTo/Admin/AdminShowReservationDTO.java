package com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Admin;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.ReservationStatus;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Client;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.RestaurantTable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AdminShowReservationDTO {

    private Long idReservation;
    private Client clientReservation;
    private ReservationStatus reservationStatus;
    private String comment;
    private Integer numberOfPeople;
    private Date dateAndTime;
    private RestaurantTable restaurantTableId;
    private Date creationDate;
}
