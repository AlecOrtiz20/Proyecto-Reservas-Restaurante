package com.example.reservas.restaurante.SistemaReservasRestaurante.Repository;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRestaurantRepository extends JpaRepository<RestaurantTable, Long> {
}
