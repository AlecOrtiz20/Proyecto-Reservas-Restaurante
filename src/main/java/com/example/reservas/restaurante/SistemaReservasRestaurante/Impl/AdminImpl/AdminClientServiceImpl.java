package com.example.reservas.restaurante.SistemaReservasRestaurante.Impl.AdminImpl;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClientDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClientFilterDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AdminClientServiceImpl {

    void deleteClient(Long id);
    void updateClient(Long id, ClientDTO clientDTO);
    Optional<Client> getClientById(Long id);
    Page<Client> getAllClients(Pageable pageable,  ClientFilterDTO clientFilterDTO);
}
