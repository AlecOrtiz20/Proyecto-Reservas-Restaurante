package com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Admin;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Admin.AdminShowReservationDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO.ShowReservationDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Reservation;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminReservationService {

    private final ReservationRepository reservationRepository;

    public Page<AdminShowReservationDTO> showReservationsAdmin(Pageable pageable){

        Page<Reservation> reservations = this.reservationRepository.findAll(pageable);

        return reservations.map(reservation -> {
            AdminShowReservationDTO reservationDTO = new AdminShowReservationDTO();

            reservationDTO.setIdReservation(reservationDTO.getIdReservation());
            reservationDTO.setClientReservation(reservation.getClientId());
            reservationDTO.setReservationStatus(reservation.getReservationStatus());
            reservationDTO.setComment(reservation.getComment());
            reservationDTO.setNumberOfPeople(reservation.getNumberOfPeople());
            reservationDTO.setDateAndTime(reservation.getDateAndTime());
            reservationDTO.setRestaurantTableId(reservation.getRestaurantTableId());
            reservationDTO.setCreationDate(reservation.getCreationDate());

            return reservationDTO;
        });
    }


}
