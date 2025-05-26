package com.example.reservas.restaurante.SistemaReservasRestaurante.Repository;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {


}
