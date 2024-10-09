package io.api.btgpactual.domain.dto.queries;

import io.api.btgpactual.utils.dto.PaginationFilter;
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
