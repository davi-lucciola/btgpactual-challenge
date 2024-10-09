package io.api.btgpactual.domain.dto.queries;

import io.api.btgpactual.utils.dto.PaginationDTO;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderDTO extends PaginationDTO {
    private Long orderId;
    private Long customerId;
    private String customer;
    private BigDecimal orderTotal;
    private Long itemQuantity;

    public OrderDTO(Long orderId, Long customerId, String customer, BigDecimal orderTotal, Long itemQuantity, Long total) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customer = customer;
        this.orderTotal = orderTotal;
        this.itemQuantity = itemQuantity;
        this.total = total;
    }
}
