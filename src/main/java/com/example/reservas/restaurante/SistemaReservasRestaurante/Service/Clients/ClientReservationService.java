package com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Clients;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO.CreateReservationDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO.ShowReservationDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.ReservationStatus;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Exceptions.ReservationNotFoundException;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Exceptions.TableUnavailableException;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Impl.ClientsImpl.ClientReservationServiceImpl;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Client;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Reservation;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.RestaurantTable;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Repository.ClientRepository;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Repository.ReservationRepository;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Admin.AdminTableRestaurantService;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Mails.MailService;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Validation.TableValidator;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@AllArgsConstructor
@Service
public class ClientReservationService implements ClientReservationServiceImpl {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final AdminTableRestaurantService tableReservate;
    private final TableValidator tableValidator;
    private final MailService mailService;

    @Override
    @Transactional
    public void createReservation(CreateReservationDTO createReservationDTO, long clientId, String email) throws MessagingException {
        //Se obtiene el cliente que va a hacer su reservacion
        Client client = this.clientRepository.getReferenceById(clientId);

        //Se obtiene la mesa la cual se va a reservar
        RestaurantTable restaurantTable = this.tableReservate.getById(createReservationDTO.getTableReservationId());

        if (reservationRepository.hasActiveReservation(client.getId())){
            throw new ReservationNotFoundException("Ya tiene una reservacion activa");
        }

        //Se valida que la mesa a reservar no este ocupada o enlimpieza
        if (!tableValidator.validateTable(restaurantTable)){

            throw new TableUnavailableException("La mesa no esta disponible");
        }

        //Valida que el numero de personas ingresado no sea mayor a la capacidad de la mesa a reservar
        if(!tableValidator.validateNumberOfPeople(restaurantTable, createReservationDTO.getNumberOfPeople())){
            throw new TableUnavailableException("Superaste la capacidad maxima de la  mesa");
        }

        //Se crea la reservacion
        Reservation reservation = new Reservation();
        reservation.setClientId(client);
        reservation.setRestaurantTableId(restaurantTable);
        reservation.setDateAndTime(createReservationDTO.getDateAndTimeReservation());
        reservation.setReservationStatus(ReservationStatus.PENDING);
        reservation.setNumberOfPeople(createReservationDTO.getNumberOfPeople());
        reservation.setComment(createReservationDTO.getCommentReservation());
        reservation.setCreationDate(new Date());

        this.mailService.sendMailClient(email, reservation.getDateAndTime().toString(), reservation.getRestaurantTableId().getLocation(), client.getFirstName(), String.valueOf(reservation.getNumberOfPeople()));

        //Se guarda la reservacion
        this.reservationRepository.save(reservation);

    }

    @Override
    public Page<ShowReservationDTO> getReservationsByClient(Client client, Pageable pageable) {

        Page<Reservation> reservations = this.reservationRepository.findByClientId(client, pageable);

        return reservations.map(reservation -> {
           ShowReservationDTO reservationDTO = new ShowReservationDTO();

           reservationDTO.setNumberOfPeople(reservation.getNumberOfPeople());
           reservationDTO.setDateAndTime(reservation.getDateAndTime());
           reservationDTO.setComment(reservation.getComment());
           reservationDTO.setLocationTable(reservation.getRestaurantTableId().getLocation());
           reservationDTO.setReservationStatus(reservation.getReservationStatus());

            return reservationDTO;
        });
    }


}
