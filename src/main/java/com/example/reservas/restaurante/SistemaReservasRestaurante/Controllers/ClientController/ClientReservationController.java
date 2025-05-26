package com.example.reservas.restaurante.SistemaReservasRestaurante.Controllers.ClientController;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO.CreateReservationDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO.ShowReservationDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Credentials;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Clients.ClientReservationService;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Service.CurrentClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/client/reservation")
public class ClientReservationController {

    private final ClientReservationService clientReservationService;
    private final CurrentClientService currentClientService;

    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@RequestBody CreateReservationDTO createReservationDTO){

        try{

            Credentials credentialsClient = this.currentClientService.getCurrentUser();

            this.clientReservationService.createReservation(createReservationDTO, credentialsClient.getIdClient().getId());

            return ResponseEntity.ok(Map.of("Message",  "La reservacion fue hecha con exito"));

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Error", "Error al crear la reservacion" + e.getMessage()));
        }
    }

    //Controlador para obtener las reservaciones hechas por el cliente autenticado
    @GetMapping("/reservations")
    public ResponseEntity<?> ReservationsClient(Pageable pageable){
        try {
            Credentials credentialsClient = this.currentClientService.getCurrentUser();

            Page<ShowReservationDTO> page = this.clientReservationService.getReservationsByClient(credentialsClient.getIdClient(), pageable);

            return ResponseEntity.ok(page);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Error", "Error al consultar" + e.getMessage()));
        }


    }

}
