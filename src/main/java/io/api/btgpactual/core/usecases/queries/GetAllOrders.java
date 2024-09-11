package io.api.btgpactual.core.usecases.queries;

import io.api.btgpactual.domain.dto.queries.OrderDTO;
import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.domain.exceptions.NoContentException;
import io.api.btgpactual.infra.repositories.queries.OrderQueryRepository;
import io.api.btgpactual.utils.annotations.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetAllOrders {
    private final OrderQueryRepository orderQueryRepository;

    public List<OrderDTO> getAllOrders(QueryOrdersFilter filter) throws NoContentException {
        List<OrderDTO> orders = orderQueryRepository.getAll(filter);

        if (orders.size() == 0) {
            throw new NoContentException();
        }

        return orders;
    }
}
