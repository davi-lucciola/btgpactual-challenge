package io.api.btgpactual.domain.entities;

import io.api.btgpactual.domain.dto.command.CreateCustomerDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer(Long id) {
        this.id = id;
    }

    public Customer(CreateCustomerDTO createCustomerDTO) {
        this.name = createCustomerDTO.name();
    }
}
