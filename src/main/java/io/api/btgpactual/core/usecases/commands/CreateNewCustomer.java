package io.api.btgpactual.core.usecases.commands;

import io.api.btgpactual.domain.dto.command.CreateCustomerDTO;
import io.api.btgpactual.domain.entities.Customer;
import io.api.btgpactual.domain.exceptions.DomainException;
import io.api.btgpactual.domain.exceptions.ValidationException;
import io.api.btgpactual.infra.repositories.commands.CustomerRepository;
import io.api.btgpactual.utils.annotations.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateNewCustomer {
    private final CustomerRepository customerRepository;

    public Customer createNewCustomer(CreateCustomerDTO createCustomerDTO) throws  ValidationException, DomainException {
        createCustomerDTO.validate();

        Customer customerDb = customerRepository.findByName(createCustomerDTO.name()).orElse(null);
        if (customerDb != null) {
            throw new DomainException("Cliente já cadastrado.");
        }

        Customer customer = new Customer(createCustomerDTO);
        customer = customerRepository.saveAndFlush(customer);

        return customer;
    }
}
