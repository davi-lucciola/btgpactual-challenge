package io.api.btgpactual.domain.dto.queries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetailDTO extends OrderDTO {
    private String items;

    public OrderDetailDTO(Long orderId, Long customerId, String customer, BigDecimal total, Long itemQuantity, String items) {
        super(orderId, customerId, customer, total, itemQuantity);
        this.items = items;
    }

    public List<OrderItemDTO> getItems() throws JsonProcessingException {
        final ObjectMapper jsonMapper = new ObjectMapper();
        return List.of(jsonMapper.readValue(this.items, OrderItemDTO[].class));
    }
}
