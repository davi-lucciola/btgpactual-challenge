package io.api.btgpactual.core.services;

import io.api.btgpactual.domain.dto.command.CreateOrderDTO;
import io.api.btgpactual.domain.dto.queries.OrderDTO;
import io.api.btgpactual.domain.dto.queries.OrderDetailDTO;
import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.domain.entities.Order;
import io.api.btgpactual.domain.exceptions.DomainException;
import io.api.btgpactual.domain.exceptions.NoContentException;
import io.api.btgpactual.domain.exceptions.NotFoundException;
import io.api.btgpactual.domain.exceptions.ValidationException;
import io.api.btgpactual.domain.services.IOrderService;
import io.api.btgpactual.core.usecases.commands.ProcessOrder;
import io.api.btgpactual.core.usecases.queries.DetailOrder;
import io.api.btgpactual.core.usecases.queries.GetAllOrders;
import io.api.btgpactual.utils.responses.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final GetAllOrders getAllUseCase;
    private final DetailOrder detailUseCase;
    private final ProcessOrder createUseCase;

    @Override
    public PaginationResponse<OrderDTO> getAllOrders(QueryOrdersFilter filter) throws NoContentException {
        return getAllUseCase.getAllOrders(filter);
    }

    @Override
    public OrderDetailDTO getOrderById(Long orderId) throws NotFoundException {
        return detailUseCase.detailOrder(orderId);
    }

    @Override
    public Order processOrder(CreateOrderDTO createOrderDTO) throws DomainException, ValidationException {
        return createUseCase.processOrder(createOrderDTO);
    }
}
