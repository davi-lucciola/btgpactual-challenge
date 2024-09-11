package io.api.btgpactual.domain.dto.queries;

import io.api.btgpactual.domain.exceptions.ValidationException;

import java.math.BigDecimal;

public record QueryOrdersFilter(Long customerId, BigDecimal minTotal, BigDecimal maxTotal) {
    public void validateCustomerIsNotNull() throws ValidationException {
        if (customerId == null) {
            throw new ValidationException("O campo \"customerId\" é obrigatório.");
        }
    }
}
