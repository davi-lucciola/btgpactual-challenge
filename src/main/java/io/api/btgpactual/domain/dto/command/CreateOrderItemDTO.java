package io.api.btgpactual.domain.dto.command;

import io.api.btgpactual.domain.dto.IRequestDTO;
import io.api.btgpactual.domain.exceptions.ValidationException;

import java.math.BigDecimal;

public record CreateOrderItemDTO(String product, Integer quantity, BigDecimal price) implements IRequestDTO {
    public void validate() throws ValidationException {
        if (product == null || product.isBlank()) {
            throw new ValidationException("O campo \"product\" é obrigatório.");
        }

        if (quantity == null) {
            throw new ValidationException("O campo \"quantity\" é obrigatório.");
        }

        if (quantity <= 0) {
            throw new ValidationException("O campo \"quantity\" deve ser maior que zero.");
        }

        if (price == null) {
            throw new ValidationException("O campo \"price\" é obrigatório.");
        }

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("O campo \"price\" deve ser maior ou igual a zero.");
        }
    }
}
