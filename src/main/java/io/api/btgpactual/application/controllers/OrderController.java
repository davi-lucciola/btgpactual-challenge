package io.api.btgpactual.application.controllers;

import io.api.btgpactual.domain.dto.queries.OrderDTO;
import io.api.btgpactual.domain.dto.queries.OrderDetailDTO;
import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.domain.exceptions.NoContentException;
import io.api.btgpactual.domain.exceptions.NotFoundException;
import io.api.btgpactual.domain.services.IOrderService;
import io.api.btgpactual.utils.responses.PaginationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Orders")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;

    @GetMapping
    @Operation(description = "Get all orders")
    public PaginationResponse<OrderDTO> getAllOrders(
            @ParameterObject QueryOrdersFilter filter
    ) throws NoContentException {
        return orderService.getAllOrders(filter);
    }

    @GetMapping("/{id}")
    @Operation(description = "Detail order and items by order id.")
    public OrderDetailDTO getOrderById(@PathVariable("id") Long orderId) throws NotFoundException {
        return orderService.getOrderById(orderId);
    }
}
