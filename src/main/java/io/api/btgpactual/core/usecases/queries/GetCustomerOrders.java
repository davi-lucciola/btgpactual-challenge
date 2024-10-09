package io.api.btgpactual.core.usecases.queries;

import io.api.btgpactual.domain.dto.queries.OrderDTO;
import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.domain.entities.Customer;
import io.api.btgpactual.domain.exceptions.NoContentException;
import io.api.btgpactual.domain.exceptions.NotFoundException;
import io.api.btgpactual.domain.exceptions.ValidationException;
import io.api.btgpactual.infra.repositories.commands.CustomerRepository;
import io.api.btgpactual.infra.repositories.queries.OrderQueryRepository;
import io.api.btgpactual.utils.annotations.UseCase;
import io.api.btgpactual.utils.dto.PaginationResponseDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetCustomerOrders {
    private final CustomerRepository customerRepository;
    private final OrderQueryRepository orderQueryRepository;

    public PaginationResponseDTO<OrderDTO> getOrdersByCustomer(QueryOrdersFilter filter) throws ValidationException, NotFoundException, NoContentException {
        filter.validate();
        Customer customer = customerRepository.findById(filter.getCustomerId()).orElse(null);

        if (customer == null) {
            throw new NotFoundException("Cliente não encontrado.");
        }

        List<OrderDTO> customerOrders = orderQueryRepository.getAll(filter);

        if (customerOrders.isEmpty()) {
            throw new NoContentException();
        }

        return new PaginationResponseDTO<>(
                customerOrders, customerOrders.get(0).getTotal(), filter.getPage(), filter.getPageSize());
    }
}
