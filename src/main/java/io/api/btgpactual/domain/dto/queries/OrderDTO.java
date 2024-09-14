package io.api.btgpactual.domain.dto.queries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.api.btgpactual.utils.responses.PaginationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends PaginationDTO {
    private Long orderId;
    private Long customerId;
    private String customer;
    private BigDecimal orderTotal;
    private Long itemQuantity;
}
