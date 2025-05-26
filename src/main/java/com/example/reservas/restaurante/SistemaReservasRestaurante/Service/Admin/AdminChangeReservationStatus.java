package com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Admin;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Admin.ChangeStatusReservationDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Exceptions.ReservationNotFoundException;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Impl.AdminImpl.AdminChangeStatusReservationImpl;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Reservation;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Repository.ReservationRepository;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Validation.ValidationStatusReservation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminChangeReservationStatus implements AdminChangeStatusReservationImpl {

    private final ReservationRepository reservationRepository;
    private final ValidationStatusReservation validationStatusReservation;

    @Override
    public void changeStatusReservation(ChangeStatusReservationDTO changeStatusDTO) {

        Reservation reservation = this.reservationRepository.findById(changeStatusDTO.getIdReservation())
                .orElseThrow(() -> new ReservationNotFoundException("La reservacion no existe"));

        if (!validationStatusReservation.validationStatus(reservation, changeStatusDTO.getReservationStatus())){
            throw new ReservationNotFoundException("El estado que tiene la reservacion es el mismo");
        }

        reservation.setReservationStatus(changeStatusDTO.getReservationStatus());

        this.reservationRepository.save(reservation);

    }
}
