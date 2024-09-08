package io.api.btgpactual.domain.dto;

import io.api.btgpactual.domain.exceptions.ValidationException;

import java.util.List;

public record CreateOrderDTO(Long orderId, Long customerId, List<CreateOrderItemDTO> items) {
    public void validate() throws ValidationException {
        if (orderId == null) {
            throw new ValidationException("O campo \"orderId\" é obrigatório.");
        }

        if (customerId == null) {
            throw new ValidationException("O campo \"customerId\" é obrigatório.");
        }

        if (items == null || items.isEmpty()) {
            throw new ValidationException("O campo \"items\" é obrigatório.");
        }

        for (CreateOrderItemDTO item : items) {
            item.validate();
        }
    }
}
