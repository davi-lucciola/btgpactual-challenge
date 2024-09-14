package io.api.btgpactual.domain.dto.queries;

import io.api.btgpactual.domain.dto.IRequestDTO;
import io.api.btgpactual.domain.exceptions.ValidationException;
import io.api.btgpactual.utils.responses.PaginationFilter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class QueryOrdersFilter extends PaginationFilter implements IRequestDTO {
    private Long customerId;
    private BigDecimal minTotal;
    private BigDecimal maxTotal;

    public QueryOrdersFilter(Long customerId, BigDecimal minTotal, BigDecimal maxTotal, Integer page, Integer pageSize) {
        super(page, pageSize);
        this.customerId = customerId;
        this.minTotal = minTotal;
        this.maxTotal = maxTotal;
    }

    @Override
    public void validate() throws ValidationException {
        if (customerId == null) {
            throw new ValidationException("O campo \"customerId\" é obrigatório.");
        }
    }
}
