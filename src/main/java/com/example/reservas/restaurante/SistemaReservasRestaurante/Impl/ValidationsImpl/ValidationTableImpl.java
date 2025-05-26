package com.example.reservas.restaurante.SistemaReservasRestaurante.Impl.ValidationsImpl;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.RestaurantTable;

public interface ValidationTableImpl {

    boolean validateTable(RestaurantTable restaurantTable);
    boolean validateNumberOfPeople(RestaurantTable restaurantTable, Integer numberOfPeople);
}
