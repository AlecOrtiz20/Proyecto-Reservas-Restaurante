package com.example.reservas.restaurante.SistemaReservasRestaurante.Impl.ClientsImpl;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO.CreateReservationDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO.ShowReservationDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Client;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientReservationServiceImpl {
    void createReservation(CreateReservationDTO createReservationDTO, long clientId, String email) throws MessagingException;

    //Nos permite obtener las reservaciones que haya realizdo el cliente autenticado
    Page<ShowReservationDTO> getReservationsByClient(Client client, Pageable pageable);

}
