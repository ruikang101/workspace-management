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

/**
 * Global exception handler. This class is used to convert server side exceptions to response
 * message with proper Http status to clients.
 */
@ControllerAdvice
public class GlobalExceptionHandler implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * Handler for {@link ResourceNotFoundException}
   *
   * @param e exception from server.
   * @return Response entity with helpful string message showing error info and http status.
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleNotfound(Exception e) {
    return new ResponseEntity<>(
        String.format("%s not found", e.getMessage()), HttpStatus.NOT_FOUND);
  }

  /**
   * Handler for {@link NotImplementedException}
   *
   * @param e exception from server.
   * @return Response entity with helpful string message showing error info and http status.
   */
  @ExceptionHandler(NotImplementedException.class)
  public ResponseEntity<Object> handleNotImplemented(Exception e) {
    return new ResponseEntity<>(
        String.format("ops something is not ready: %S", e.getMessage()),
        HttpStatus.SERVICE_UNAVAILABLE);
  }

  /**
   * Handler for {@link InternalException}
   *
   * @param e exception from server.
   * @return Response entity with helpful string message showing error info and http status.
   */
  @ExceptionHandler(InternalException.class)
  public ResponseEntity<Object> handleInternalException(Exception e) {
    return new ResponseEntity<>(
        "Oooops! Internal Error. Please contact the service owner!",
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handler for {@link NotImplementedException}
   *
   * @param e exception from server.
   * @return Response entity with helpful string message showing error info and http status.
   */
  @ExceptionHandler(InvalidInputException.class)
  public ResponseEntity<Object> handleInvalidInputException(Exception e) {
    return new ResponseEntity<>(
        String.format("Invalid input:\n %s", e.getMessage()), HttpStatus.BAD_REQUEST);
  }
}
