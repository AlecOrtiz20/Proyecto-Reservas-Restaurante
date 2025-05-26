package com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.Filter;

import com.example.reservas.restaurante.SistemaReservasRestaurante.DTo.ClientFilterDTO;
import com.example.reservas.restaurante.SistemaReservasRestaurante.Models.Client;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ClientSpecifications {

    public static Specification<Client> filterBy(ClientFilterDTO clientFilterDTO){

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (clientFilterDTO.getName() != null && !clientFilterDTO.getName().isEmpty()){

                Predicate namePredicate = criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + clientFilterDTO.getName() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),  "%" + clientFilterDTO.getName() + "%")
                );

               predicates.add(namePredicate);
            }

            if (clientFilterDTO.getClientStatus() != null){
                predicates.add(criteriaBuilder.equal(root.get("clientStatus"), clientFilterDTO.getClientStatus()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
