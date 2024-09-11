package io.api.btgpactual.domain.dto.command;

import io.api.btgpactual.domain.dto.IRequestDTO;
import io.api.btgpactual.domain.exceptions.ValidationException;

public record CreateCustomerDTO(String name) implements IRequestDTO {
    @Override
    public void validate() throws ValidationException {
        if (name == null) {
            throw new ValidationException("O campo \"name\" é obrigatório.");
        }
    }
}
