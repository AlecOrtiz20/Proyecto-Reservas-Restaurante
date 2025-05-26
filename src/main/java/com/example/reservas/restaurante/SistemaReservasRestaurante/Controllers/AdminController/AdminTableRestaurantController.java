package com.example.reservas.restaurante.SistemaReservasRestaurante.Controllers.AdminController;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Admin.AdminCreationTableDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO.ClientTableRestaurantDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Admin.AdminTableRestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/table")
public class AdminTableRestaurantController {

    private final AdminTableRestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseEntity<?> createTableRestaurant(@RequestBody AdminCreationTableDTO adminCreationTableDTO){
        try {


            return ResponseEntity.ok(Map.of("Message", this.restaurantService.create(adminCreationTableDTO)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Error", "Error al crear la mesa"));
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteTable(@RequestParam Long id){
        try{
            return ResponseEntity.ok(Map.of("Message", this.restaurantService.delete(id)));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Error", "Error al eliminar"));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateTable(@RequestBody AdminCreationTableDTO adminCreationTableDTO){
        try {
            return ResponseEntity.ok(Map.of("Message" , this.restaurantService.update(adminCreationTableDTO)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Error", e.getMessage()));
        }
    }

}
