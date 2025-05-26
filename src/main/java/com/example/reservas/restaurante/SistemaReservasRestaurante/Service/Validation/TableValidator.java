package com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Validation;

import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.TableStatus;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Impl.ValidationsImpl.ValidationTableImpl;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.RestaurantTable;
import org.springframework.stereotype.Service;

@Service
public class TableValidator implements ValidationTableImpl {
    
    @Override
    public boolean validateTable(RestaurantTable restaurantTable) {

        return restaurantTable.getTableStatus() != TableStatus.CLEANING && restaurantTable.getTableStatus() != TableStatus.RESERVED;
    }

    @Override
    public boolean validateNumberOfPeople(RestaurantTable restaurantTable, Integer numberOfPeople) {
        return numberOfPeople < restaurantTable.getCapacity();
    }
}
