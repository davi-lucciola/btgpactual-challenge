package io.api.btgpactual.domain.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoContentException extends Exception {
    public NoContentException(String message) {
        super(message);
    }
}
