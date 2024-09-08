package io.api.btgpactual.mocks;

import io.api.btgpactual.domain.dto.CreateOrderDTO;
import io.api.btgpactual.domain.entities.Order;

import static io.api.btgpactual.mocks.OrderItemsMock.createOrderItemsDTO;

public class OrderMock {
    public static CreateOrderDTO createOrderDTO() {
        return new CreateOrderDTO(1L, 2L, createOrderItemsDTO());
    }

    public static Order orderEntity() {
        return new Order(createOrderDTO());
    }
}
