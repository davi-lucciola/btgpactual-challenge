package io.api.btgpactual.domain.dto.queries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private Long customerId;
    private String customer;
    private BigDecimal total;
    private Long itemQuantity;
    private String items;

    public List<OrderItemDTO> getItems() throws JsonProcessingException {
        final ObjectMapper jsonMapper = new ObjectMapper();
        return List.of(jsonMapper.readValue(this.items, OrderItemDTO[].class));
    }
}
