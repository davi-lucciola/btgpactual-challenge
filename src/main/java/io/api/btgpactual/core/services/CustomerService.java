package io.api.btgpactual.core.services;

import io.api.btgpactual.core.usecases.commands.CreateNewCustomer;
import io.api.btgpactual.core.usecases.queries.GetCustomerOrders;
import io.api.btgpactual.domain.dto.command.CreateCustomerDTO;
import io.api.btgpactual.domain.dto.queries.OrderDTO;
import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.domain.entities.Customer;
import io.api.btgpactual.domain.exceptions.DomainException;
import io.api.btgpactual.domain.exceptions.NoContentException;
import io.api.btgpactual.domain.exceptions.NotFoundException;
import io.api.btgpactual.domain.exceptions.ValidationException;
import io.api.btgpactual.domain.services.ICustomerService;
import io.api.btgpactual.utils.responses.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final GetCustomerOrders customerOrdersUseCase;
    private final CreateNewCustomer createNewUseCase;

    @Override
    public PaginationResponse<OrderDTO> getOrdersByCostumer(QueryOrdersFilter filter) throws ValidationException, NotFoundException, NoContentException {
        return customerOrdersUseCase.getOrdersByCustomer(filter);
    }

    @Override
    public Customer createNewCustomer(CreateCustomerDTO createCustomerDTO) throws ValidationException, DomainException {
        return createNewUseCase.createNewCustomer(createCustomerDTO);
    }
}
