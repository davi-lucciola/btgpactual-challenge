package io.api.btgpactual.domain.dto.queries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDTO {
    private Long id;
    private String product;
    private Integer quantity;
    private BigDecimal price;
}
