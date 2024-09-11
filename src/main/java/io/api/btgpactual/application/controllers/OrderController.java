package io.api.btgpactual.application.controllers;

import io.api.btgpactual.domain.dto.queries.OrderDTO;
import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.domain.exceptions.NoContentException;
import io.api.btgpactual.domain.exceptions.NotFoundException;
import io.api.btgpactual.domain.services.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;

    @GetMapping
    public List<OrderDTO> getAllOrders(QueryOrdersFilter filter) throws NoContentException {
        return orderService.getAllOrders(filter);
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable("id") Long orderId) throws NotFoundException {
        return orderService.getOrderById(orderId);
    }
}
