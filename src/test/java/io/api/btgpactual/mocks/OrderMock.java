package io.api.btgpactual.mocks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.api.btgpactual.domain.dto.command.CreateOrderDTO;
import io.api.btgpactual.domain.dto.queries.OrderDTO;
import io.api.btgpactual.domain.dto.queries.OrderDetailDTO;
import io.api.btgpactual.domain.dto.queries.OrderItemDTO;
import io.api.btgpactual.domain.entities.Order;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static io.api.btgpactual.mocks.OrderItemsMock.CreateOrderItemsDTO;

public class OrderMock {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static CreateOrderDTO CreateOrderDTOMock() {
        return new CreateOrderDTO(1L, 2L, CreateOrderItemsDTO());
    }

    public static Order OrderEntityMock() {
        return new Order(CreateOrderDTOMock());
    }

    public static List<OrderDTO> OrdersDTOMock() {
        return List.of(new OrderDTO[]{
                new OrderDTO(1L, 2L, "Fernanda",
                        BigDecimal.valueOf(10L), 1L, 1L)
        });
    }

    public static OrderDetailDTO OrderDetailDTOMock() throws JsonProcessingException {
        OrderItemDTO[] orderItems = new OrderItemDTO[]{
                new OrderItemDTO(1L, "Camiseta", 4, BigDecimal.ONE),
                new OrderItemDTO(2L, "Short", 2, BigDecimal.valueOf(2L))
        };

        BigDecimal total = Arrays.stream(orderItems)
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new OrderDetailDTO(1L, 1L, "Fernanda", total, 2L, mapper.writeValueAsString(orderItems));
    }
}
