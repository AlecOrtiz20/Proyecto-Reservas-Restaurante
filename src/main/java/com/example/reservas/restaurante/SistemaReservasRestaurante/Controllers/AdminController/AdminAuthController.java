package com.example.reservas.restaurante.SistemaReservasRestaurante.Controllers.AdminController;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Admin.AdminRequestDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.LoginDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth/admin")
public class AdminAuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> RegisterAdmin(AdminRequestDTO adminRequestDTO){

        try {
            String tokenAdmin = this.authService.registerAdmin(adminRequestDTO);
            return ResponseEntity.ok(Map.of("Token Admin:", tokenAdmin));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Error register admin", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(LoginDTO loginDTO){
        try {

            String tokenAdmin = this.authService.LonginAdmin(loginDTO);

            return ResponseEntity.ok(Map.of("Token Admin", tokenAdmin));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Error admin login", e.getMessage()));
        }
    }


}
