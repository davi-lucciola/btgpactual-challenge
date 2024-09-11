package io.api.btgpactual.core.usecases.queries;

import io.api.btgpactual.domain.dto.queries.CustomerOrdersDTO;
import io.api.btgpactual.domain.dto.queries.OrderDTO;
import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.domain.entities.Customer;
import io.api.btgpactual.domain.exceptions.NoContentException;
import io.api.btgpactual.domain.exceptions.NotFoundException;
import io.api.btgpactual.domain.exceptions.ValidationException;
import io.api.btgpactual.infra.repositories.commands.CustomerRepository;
import io.api.btgpactual.infra.repositories.queries.OrderQueryRepository;
import io.api.btgpactual.utils.annotations.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetCustomerOrders {
    private final CustomerRepository customerRepository;
    private final OrderQueryRepository orderQueryRepository;

    public CustomerOrdersDTO getOrdersByCustomer(QueryOrdersFilter filter) throws ValidationException, NotFoundException, NoContentException {
        filter.validateCustomerIsNotNull();

        Customer customer = customerRepository.findById(filter.customerId()).orElse(null);

        if (customer == null) {
            throw new NotFoundException("Cliente não encontrado.");
        }

        List<OrderDTO> customerOrders = orderQueryRepository.getAll(filter);

        if (customerOrders.size() == 0) {
            throw new NoContentException();
        }

        return new CustomerOrdersDTO(
                Long.valueOf(String.valueOf(customerOrders.size())), customerOrders);
    }
}
