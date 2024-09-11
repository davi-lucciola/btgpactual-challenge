package io.api.btgpactual.domain.dto.queries;

import java.util.List;

public record CustomerOrdersDTO(Long ordersQuantity, List<OrderDTO> orders) {
}
