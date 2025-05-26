package com.example.reservas.restaurante.SistemaReservasRestaurante.Controllers.AdminController;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Admin.ChangeStatusReservationDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Admin.AdminChangeReservationStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/reservation")
public class AdminReservationController {

    private final AdminChangeReservationStatus changeReservationStatus;

    //Controlador par cambiar el estado de la reservacion que realizo un cliente
    @PostMapping("/statusChange")
    public ResponseEntity<?> changeStatus(@RequestBody ChangeStatusReservationDTO changeStatusReservationDTO){
        try {
            this.changeReservationStatus.changeStatusReservation(changeStatusReservationDTO);
            return ResponseEntity.ok(Map.of("Message", "Estado de la reservcion cambiado con exito..."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Error", "Error al cambiar el estdo de la reservacion: " + e.getMessage()));
        }
    }

}
