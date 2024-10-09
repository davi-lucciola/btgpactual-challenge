package io.api.btgpactual.domain.services;

import io.api.btgpactual.domain.dto.command.CreateCustomerDTO;
import io.api.btgpactual.domain.dto.queries.OrderDTO;
import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.domain.entities.Customer;
import io.api.btgpactual.domain.exceptions.DomainException;
import io.api.btgpactual.domain.exceptions.NoContentException;
import io.api.btgpactual.domain.exceptions.NotFoundException;
import io.api.btgpactual.domain.exceptions.ValidationException;
import io.api.btgpactual.utils.dto.PaginationResponseDTO;

public interface ICustomerService {
    PaginationResponseDTO<OrderDTO> getOrdersByCostumer(QueryOrdersFilter filter) throws ValidationException, NotFoundException, NoContentException;
    Customer createNewCustomer(CreateCustomerDTO createCustomerDTO) throws ValidationException, DomainException;
}
