package io.api.btgpactual.mocks;

import io.api.btgpactual.domain.dto.command.CreateOrderItemDTO;
import io.api.btgpactual.domain.entities.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsMock {
    public static List<CreateOrderItemDTO> CreateOrderItemsDTO() {
        List<CreateOrderItemDTO> orderItems = new ArrayList<>();

        orderItems.add(new CreateOrderItemDTO("Ração de Gato", 8, BigDecimal.TEN));
        orderItems.add(new CreateOrderItemDTO("Sachê de Frango", 10, BigDecimal.ONE));

        return orderItems;
    }

    public static List<OrderItem> orderItemsEntities() {
        List<OrderItem> orderItems = CreateOrderItemsDTO().stream().map(OrderItem::new).toList();

        orderItems.get(0).setId(1L);
        orderItems.get(1).setId(2L);

        return orderItems;
    }
}
