package io.api.btgpactual.domain.services;

import io.api.btgpactual.domain.dto.command.CreateOrderDTO;
import io.api.btgpactual.domain.dto.queries.OrderDTO;
import io.api.btgpactual.domain.dto.queries.OrderDetailDTO;
import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.domain.entities.Order;
import io.api.btgpactual.domain.exceptions.DomainException;
import io.api.btgpactual.domain.exceptions.NoContentException;
import io.api.btgpactual.domain.exceptions.NotFoundException;
import io.api.btgpactual.domain.exceptions.ValidationException;
import io.api.btgpactual.utils.responses.PaginationResponse;

import java.util.List;

public interface IOrderService {
    PaginationResponse<OrderDTO> getAllOrders(QueryOrdersFilter filter) throws NoContentException;
    OrderDetailDTO getOrderById(Long orderId) throws NotFoundException;
    Order processOrder(CreateOrderDTO createOrderDTO) throws DomainException, ValidationException;
}
