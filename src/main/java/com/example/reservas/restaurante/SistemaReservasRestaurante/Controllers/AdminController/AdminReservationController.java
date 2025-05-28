package com.example.reservas.restaurante.SistemaReservasRestaurante.Controllers.AdminController;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Admin.ChangeStatusReservationDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Admin.AdminChangeReservationStatus;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Admin.AdminReservationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/reservation")
public class AdminReservationController {

    private final AdminChangeReservationStatus changeReservationStatus;
    private final AdminReservationService reservationService;

    //Controlador par cambiar el estado de la reservacion que realiza un cliente
    @PostMapping("/statusChange")
    public ResponseEntity<?> changeStatus(@RequestBody ChangeStatusReservationDTO changeStatusReservationDTO){
        try {
            this.changeReservationStatus.changeStatusReservation(changeStatusReservationDTO);
            return ResponseEntity.ok(Map.of("Message", "Estado de la reservcion cambiado con exito..."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Error", "Error al cambiar el estdo de la reservacion: " + e.getMessage()));
        }
    }

    //Controldor para mostrar todas las reservaciones realizadas por los clientes
    @GetMapping("/all")
    public ResponseEntity<?> showReservations(Pageable pageable){
        try {
            return ResponseEntity.ok(Map.of("Reservciones Creadas: ", this.reservationService.showReservationsAdmin(pageable)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Error: ", e.getMessage()));
        }
    }


}
