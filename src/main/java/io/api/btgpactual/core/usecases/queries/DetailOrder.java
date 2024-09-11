package io.api.btgpactual.core.usecases.queries;

import io.api.btgpactual.domain.dto.queries.OrderDTO;
import io.api.btgpactual.domain.exceptions.NotFoundException;
import io.api.btgpactual.infra.repositories.queries.OrderQueryRepository;
import io.api.btgpactual.utils.annotations.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DetailOrder {
    private final OrderQueryRepository orderQueryRepository;

    public OrderDTO detailOrder(Long orderId) throws NotFoundException {
        OrderDTO orderDTO = orderQueryRepository.getById(orderId);

        if (orderDTO == null) {
            throw new NotFoundException("Pedido não encontrado.");
        }

        return orderDTO;
    }
}
