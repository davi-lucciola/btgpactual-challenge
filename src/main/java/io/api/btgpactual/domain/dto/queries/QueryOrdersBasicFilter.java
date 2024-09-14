package io.api.btgpactual.domain.dto.queries;

import io.api.btgpactual.utils.responses.PaginationFilter;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class QueryOrdersBasicFilter extends PaginationFilter {
    private BigDecimal minTotal;
    private BigDecimal maxTotal;
}
