package io.api.btgpactual.application.controllers;

import io.api.btgpactual.domain.dto.command.CreateCustomerDTO;
import io.api.btgpactual.domain.dto.queries.OrderDTO;
import io.api.btgpactual.domain.dto.queries.QueryOrdersBasicFilter;
import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.domain.exceptions.DomainException;
import io.api.btgpactual.domain.exceptions.NoContentException;
import io.api.btgpactual.domain.exceptions.NotFoundException;
import io.api.btgpactual.domain.exceptions.ValidationException;
import io.api.btgpactual.domain.services.ICustomerService;
import io.api.btgpactual.utils.dto.PaginationResponseDTO;
import io.api.btgpactual.utils.dto.ResponseDTO;;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customers")
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;

    @GetMapping("/{id}/orders")
    @Operation(description = "Get customer orders by customer id.")
    public PaginationResponseDTO<OrderDTO> getMyOrders(
            @PathVariable("id") Long customerId, @ParameterObject QueryOrdersBasicFilter filterBasic
    ) throws ValidationException, NotFoundException, NoContentException {
        QueryOrdersFilter filter = new QueryOrdersFilter(customerId, filterBasic.getMinTotal(),
                filterBasic.getMaxTotal(), filterBasic.getPage(), filterBasic.getPageSize());

        return customerService.getOrdersByCostumer(filter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create new customer.")
    public ResponseDTO createNewCustomer(
            @RequestBody CreateCustomerDTO createCustomerDTO
    ) throws DomainException, ValidationException {
        Long customerId = customerService.createNewCustomer(createCustomerDTO).getId();
        return new ResponseDTO("Cliente cadastrado com sucesso.", customerId);
    }
}
