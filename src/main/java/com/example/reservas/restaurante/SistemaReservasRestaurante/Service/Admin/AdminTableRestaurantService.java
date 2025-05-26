package com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Admin;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Admin.AdminCreationTableDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO.ClientTableRestaurantDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.TableStatus;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Impl.CrudServiceImpl;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Client;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.RestaurantTable;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Repository.TableRestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class AdminTableRestaurantService implements CrudServiceImpl<AdminCreationTableDTO, RestaurantTable> {

    private final TableRestaurantRepository restaurantTableRepository;

    @Override
    public AdminCreationTableDTO create(AdminCreationTableDTO adminCreationTableDTO) {

        RestaurantTable tableCreate = new RestaurantTable();
        tableCreate.setTableStatus(adminCreationTableDTO.getTableStatus());
        tableCreate.setCapacity(adminCreationTableDTO.getCapacity());
        tableCreate.setLocation(adminCreationTableDTO.getLocation());
        tableCreate.setCreationDate(new Date());

        this.restaurantTableRepository.save(tableCreate);

        return adminCreationTableDTO;
    }

    @Override
    public AdminCreationTableDTO delete(Long id) {

        RestaurantTable tableDelete = this.getById(id);

        tableDelete.setTableStatus(TableStatus.OUT_OF_SERVICE);

        this.restaurantTableRepository.save(tableDelete);
        return null;
    }

    @Override
    public RestaurantTable getById(Long id) {

        return this.restaurantTableRepository.findById(id).orElseThrow(
                () -> new RuntimeException("La mesa de este restaurante no existe")
        );
    }

    @Override
    public AdminCreationTableDTO update(AdminCreationTableDTO adminCreationTableDTO) {
        RestaurantTable tableUpdate = this.getById(adminCreationTableDTO.getId());

        tableUpdate.setLocation(adminCreationTableDTO.getLocation());
        tableUpdate.setTableStatus(adminCreationTableDTO.getTableStatus());
        tableUpdate.setCapacity(adminCreationTableDTO.getCapacity());

        this.restaurantTableRepository.save(tableUpdate);

        return adminCreationTableDTO;
    }
}
