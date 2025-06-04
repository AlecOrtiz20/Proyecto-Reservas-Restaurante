package com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Admin;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO.ClientDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClientFilterDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Filter.ClientSpecifications;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.ClientStatus;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Impl.AdminImpl.AdminClientServiceImpl;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Client;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AdminClientService implements AdminClientServiceImpl {

    private final ClientRepository clientRepository;


    @Override
    public void deleteClient(Long id) {
        //Se obtiene el cliente que se va a bloquear
        Client clientBlocked = getClientById(id).orElseThrow();

        clientBlocked.setClientStatus(ClientStatus.BLOCKED);

        this.clientRepository.save(clientBlocked);
    }

    @Override
    public void updateClient(Long id, ClientDTO clientDTO) {

        //Se obtiene el cliente que se va a actualizar
        Client clientUpdate = getClientById(id).orElseThrow();

        clientUpdate.setDateOfBirth(clientDTO.getDateOfBirth());
        clientUpdate.setPhone(clientDTO.getPhone());
        clientUpdate.setAddress(clientDTO.getAddress());
        clientUpdate.setCountry(clientDTO.getCountry());
        clientUpdate.setCity(clientDTO.getCity());

        this.clientRepository.save(clientUpdate);
    }

    @Override
    public Optional<Client> getClientById(Long id) {

        return Optional.ofNullable(clientRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("El cliente no existe")));
    }


    @Override
    public Page<Client> getAllClients(Pageable pageable, ClientFilterDTO clientFilterDTO) {

        Specification<Client> specification = ClientSpecifications.filterBy(clientFilterDTO);

        return this.clientRepository.findAll(specification, pageable);
    }
}
