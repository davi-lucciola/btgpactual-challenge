package io.api.btgpactual.config;

import io.api.btgpactual.core.dto.ResponseDTO;
import io.api.btgpactual.core.exceptions.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler({ DomainException.class })
    public ResponseEntity<ResponseDTO> handleDomainException(Exception exception) {
        return new ResponseEntity<>(new ResponseDTO(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
