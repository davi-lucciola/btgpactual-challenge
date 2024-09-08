package io.api.btgpactual.domain.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DomainException extends Exception {
    public DomainException(String message) {
        super(message);
    }
}
