package io.api.btgpactual.domain.dto;

import io.api.btgpactual.domain.exceptions.ValidationException;

public interface IRequestDTO {
    void validate() throws ValidationException;
}
