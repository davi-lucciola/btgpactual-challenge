package io.api.btgpactual.domain.dto.queries;

import java.math.BigDecimal;

public record QueryOrdersBasicFilter(BigDecimal minTotal, BigDecimal maxTotal) {
}
