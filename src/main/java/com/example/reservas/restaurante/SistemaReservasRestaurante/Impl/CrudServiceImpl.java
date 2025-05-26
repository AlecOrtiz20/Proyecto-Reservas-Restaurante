package com.example.reservas.restaurante.SistemaReservasRestaurante.Impl;

public interface CrudServiceImpl <DTO, Entity>{
    DTO create(DTO dto);
    DTO delete(Long id);
    Entity getById(Long id);
    DTO update( DTO dto);
}
