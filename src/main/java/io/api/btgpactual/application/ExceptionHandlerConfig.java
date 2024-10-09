package io.api.btgpactual.application;

import io.api.btgpactual.domain.exceptions.DomainException;
import io.api.btgpactual.domain.exceptions.NoContentException;
import io.api.btgpactual.domain.exceptions.NotFoundException;
import io.api.btgpactual.utils.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler({ DomainException.class })
    public ResponseEntity<ResponseDTO> handleDomainException(Exception exception) {
        return new ResponseEntity<>(new ResponseDTO(exception.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ NoContentException.class })
    public ResponseEntity<List> handleNoContentException(Exception exception) {
        return new ResponseEntity<>(new ArrayList(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<ResponseDTO> handleNotFoundException(Exception exception) {
        return new ResponseEntity<>(new ResponseDTO(exception.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ResponseDTO> handleGenericException(Exception exception) {
        return new ResponseEntity<>(new ResponseDTO(exception.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

}