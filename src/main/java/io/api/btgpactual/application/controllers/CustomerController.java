package io.api.btgpactual.application.controllers;

import io.api.btgpactual.domain.dto.command.CreateCustomerDTO;
import io.api.btgpactual.domain.dto.queries.CustomerOrdersDTO;
import io.api.btgpactual.domain.dto.queries.QueryOrdersBasicFilter;
import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.domain.exceptions.DomainException;
import io.api.btgpactual.domain.exceptions.NoContentException;
import io.api.btgpactual.domain.exceptions.NotFoundException;
import io.api.btgpactual.domain.exceptions.ValidationException;
import io.api.btgpactual.domain.services.ICustomerService;
import io.api.btgpactual.utils.responses.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;

    @GetMapping("/{id}/orders")
    public CustomerOrdersDTO getMyOrders(
            @PathVariable("id") Long customerId, QueryOrdersBasicFilter filterBasic
    ) throws ValidationException, NotFoundException, NoContentException {
        QueryOrdersFilter filter = new QueryOrdersFilter(
                customerId, filterBasic.minTotal(), filterBasic.maxTotal());

        return customerService.getOrdersByCostumer(filter);
    }

    @PostMapping
    public ResponseDTO createNewCustomer(
            @RequestBody CreateCustomerDTO createCustomerDTO
    ) throws DomainException, ValidationException {
        customerService.createNewCustomer(createCustomerDTO);
        return new ResponseDTO("Cliente cadastrado com sucesso.");
    }
}
