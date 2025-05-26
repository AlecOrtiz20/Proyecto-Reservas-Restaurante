package com.example.reservas.restaurante.SistemaReservasRestaurante.Service;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO.ClientRequestDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.LoginDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.ClientStatus;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.RolesUser;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Impl.AuthServiceImpl;
import com.example.reservas.restaurante.SistemaReservasRestaurante.JWT.JwtService;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Client;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Credentials;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Repository.ClientRepository;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Repository.CredentialsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService implements AuthServiceImpl {

    private final ClientRepository clientRepository;
    private final CredentialsRepository credentiaslRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(ClientRepository clientRepository, CredentialsRepository credentialsRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.credentiaslRepository = credentialsRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String registerClient(ClientRequestDTO clientRequestDTO) {
        Client client = Client.builder().firstName(clientRequestDTO.getClientDTO().getFirstName())
                .lastName(clientRequestDTO.getClientDTO().getLastName())
                .phone(clientRequestDTO.getClientDTO().getPhone())
                .address(clientRequestDTO.getClientDTO().getAddress())
                .city(clientRequestDTO.getClientDTO().getCity())
                .country(clientRequestDTO.getClientDTO().getCountry())
                .dateOfBirth(clientRequestDTO.getClientDTO().getDateOfBirth())
                .clientStatus(ClientStatus.ACTIVE)
                .creationDate(new Date())
                .build();

        this.clientRepository.save(client);

        Credentials credentials = Credentials.builder()
                .email(clientRequestDTO.getCredentialsDTO().getEmail())
                .username(clientRequestDTO.getCredentialsDTO().getUsername())
                .password(this.passwordEncoder.encode(clientRequestDTO.getCredentialsDTO().getPassword()))
                .rolesUser(RolesUser.USER)
                .creatioDate(new Date())
                .idClient(client)
                .build();

        this.credentiaslRepository.save(credentials);

        return this.jwtService.generateToken(clientRequestDTO.getCredentialsDTO().getUsername(), clientRequestDTO.getCredentialsDTO().getRolesUser().name());

    }

    @Override
    public String loginClient(LoginDTO loginDTO){

        Credentials credentials = this.credentiaslRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow();

        if (!passwordEncoder.matches(loginDTO.getPassword(), credentials.getPassword())){

            throw new RuntimeException("Las credenciales son incorrectas");
        }

        return this.jwtService.generateToken(credentials.getUsername(), credentials.getRolesUser().name());
    }


}
