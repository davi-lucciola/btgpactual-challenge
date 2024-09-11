package io.api.btgpactual.domain.services;

import io.api.btgpactual.domain.dto.command.CreateOrderDTO;
import io.api.btgpactual.domain.dto.queries.OrderDTO;
import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.domain.entities.Order;
import io.api.btgpactual.domain.exceptions.DomainException;
import io.api.btgpactual.domain.exceptions.NoContentException;
import io.api.btgpactual.domain.exceptions.NotFoundException;
import io.api.btgpactual.domain.exceptions.ValidationException;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> getAllOrders(QueryOrdersFilter filter) throws NoContentException;
    OrderDTO getOrderById(Long orderId) throws NotFoundException;
    Order processOrder(CreateOrderDTO createOrderDTO) throws DomainException, ValidationException;
}
