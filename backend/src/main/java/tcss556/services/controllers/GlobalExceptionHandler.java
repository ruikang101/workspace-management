package tcss556.services.controllers;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import tcss556.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final long serialVersionUID = 1L;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotfound(Exception e){
        return new ResponseEntity<>(String.format("%s not found", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<Object> handleNotImplemented(Exception e){
        return new ResponseEntity<>(String.format("ops something is not ready: %S", e.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
