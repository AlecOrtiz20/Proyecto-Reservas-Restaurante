package com.example.reservas.restaurante.SistemaReservasRestaurante.Service;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Admin.AdminRequestDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClentDTO.ClientRequestDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.LoginDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.AdminStatus;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.ClientStatus;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Enum.RolesUser;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Impl.AuthServiceImpl;
import com.example.reservas.restaurante.SistemaReservasRestaurante.JWT.JwtService;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Admin;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.AdminCredentials;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Client;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Credentials;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Repository.AdminCredentialsRepository;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Repository.AdminRepository;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Repository.ClientRepository;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Repository.CredentialsRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@AllArgsConstructor
public class AuthService implements AuthServiceImpl {

    private final ClientRepository clientRepository;
    private final CredentialsRepository credentiaslRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final AdminCredentialsRepository adminCredentialsRepository;


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
                .email(clientRequestDTO.getCredentialsClientDTO().getEmail())
                .username(clientRequestDTO.getCredentialsClientDTO().getUsername())
                .password(this.passwordEncoder.encode(clientRequestDTO.getCredentialsClientDTO().getPassword()))
                .rolesUser(RolesUser.USER)
                .creatioDate(new Date())
                .idClient(client)
                .build();

        this.credentiaslRepository.save(credentials);

        return this.jwtService.generateToken(credentials.getUsername(), credentials.getRolesUser().name());

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

    @Override
    @Transactional
    public String registerAdmin(AdminRequestDTO adminRequestDTO) {
            Admin admin = Admin.builder().firstName(adminRequestDTO.getAdminDTO().getFirstName())
                .lastName(adminRequestDTO.getAdminDTO().getLastName())
                .phone(adminRequestDTO.getAdminDTO().getPhone())
                .address(adminRequestDTO.getAdminDTO().getAddress())
                .city(adminRequestDTO.getAdminDTO().getCity())
                .country(adminRequestDTO.getAdminDTO().getCountry())
                .dateOfBirth(adminRequestDTO.getAdminDTO().getDateOfBirth())
                .adminStatus(AdminStatus.PENDING_ACTIVATION)
                .creationDate(new Date())
                .build();
            this.adminRepository.save(admin);

        AdminCredentials credentials = AdminCredentials.builder()
                .email(adminRequestDTO.getCredentialsAdminDTO().getEmail())
                .username(adminRequestDTO.getCredentialsAdminDTO().getUsername())
                .password(this.passwordEncoder.encode(adminRequestDTO.getCredentialsAdminDTO().getPassword()))
                .admin(admin)
                .rolesUser(RolesUser.ADMIN)
                .creatonDate(new Date())
                .build();
        this.adminCredentialsRepository.save(credentials);

        return this.jwtService.generateToken(credentials.getUsername(), credentials.getRolesUser().name());

    }

    @Override
    public String LonginAdmin(LoginDTO loginDTO) {
        AdminCredentials adminCredentials = this.adminCredentialsRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("El administrador con este usrname no existe"));

        if (!adminCredentials.getPassword().matches(loginDTO.getPassword())){
            throw new UsernameNotFoundException("Las credenciales son incorrectas");
        }

        return this.jwtService.generateToken(adminCredentials.getUsername(), adminCredentials.getRolesUser().name());

    }


}
