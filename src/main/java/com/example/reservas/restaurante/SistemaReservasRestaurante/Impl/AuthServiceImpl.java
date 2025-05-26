package com.example.reservas.restaurante.SistemaReservasRestaurante.Impl;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO.ClientRequestDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.LoginDTO;

public interface AuthServiceImpl {
    String registerClient(ClientRequestDTO clientRequestDTO);
    String loginClient(LoginDTO loginDTO);
}
