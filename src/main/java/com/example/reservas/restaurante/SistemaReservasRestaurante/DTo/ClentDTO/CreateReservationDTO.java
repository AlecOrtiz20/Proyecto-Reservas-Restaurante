package com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO;

import lombok.Data;

import java.util.Date;

@Data
public class CreateReservationDTO {

    private Long tableReservationId;
    private Date dateAndTimeReservation;
    private Integer numberOfPeople;
    private String commentReservation;

}
