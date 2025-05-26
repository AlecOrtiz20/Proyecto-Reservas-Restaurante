package com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.ReservationStatus;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ShowReservationDTO{


    private Integer numberOfPeople;
    private Date dateAndTime;
    private String comment;
    private String locationTable;
    private ReservationStatus reservationStatus;


}
