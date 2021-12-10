package tcss556.services.controllers;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tcss556.services.exceptions.InternalException;
import tcss556.services.exceptions.InvalidInputException;
import tcss556.services.exceptions.ResourceNotFoundException;

import java.io.Serializable;

@ControllerAdvice
public class GlobalExceptionHandler implements Serializable {
  private static final long serialVersionUID = 1L;

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleNotfound(Exception e) {
    return new ResponseEntity<>(
        String.format("%s not found", e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NotImplementedException.class)
  public ResponseEntity<Object> handleNotImplemented(Exception e) {
    return new ResponseEntity<>(
        String.format("ops something is not ready: %S", e.getMessage()),
        HttpStatus.SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler(InternalException.class)
  public ResponseEntity<Object> handleInternalException(Exception e) {
    return new ResponseEntity<>(
        "Oooops! Internal Error. Please contact the service owner!",
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(InvalidInputException.class)
  public ResponseEntity<Object> handleInvalidInputException(Exception e) {
    return new ResponseEntity<>(
        String.format("Invalid input:\n %s", e.getMessage()), HttpStatus.BAD_REQUEST);
  }
}
