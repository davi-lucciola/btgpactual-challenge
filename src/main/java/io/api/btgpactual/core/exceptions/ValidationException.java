package io.api.btgpactual.core.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidationException extends Exception{
    public ValidationException(String message) {
        super(message);
    }
}
