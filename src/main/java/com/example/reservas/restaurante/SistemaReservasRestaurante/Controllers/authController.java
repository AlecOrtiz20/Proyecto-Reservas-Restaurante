package com.example.reservas.restaurante.SistemaReservasRestaurante.Controllers;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO.ClientRequestDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.LoginDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/authpublic")
public class authController {

    private final AuthService authService;

    public authController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveClient(@RequestBody ClientRequestDTO clientRequestDTO){

        try{

            String token = this.authService.registerClient(clientRequestDTO);

            return ResponseEntity.ok(Map.of("Token: ", token));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("ERROR", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginClient(@RequestBody LoginDTO loginDTO){

        try {

            String token = this.authService.loginClient(loginDTO);

            return ResponseEntity.ok(Map.of("Token", token));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Error", e.getMessage()));

        }
    }

}
