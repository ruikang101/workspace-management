package tcss556.services.exceptions;

/** InternalException is used to indicate error related to bad user input. */
public class InvalidInputException extends RuntimeException {
  public InvalidInputException() {
    super();
  }

  public InvalidInputException(String message) {
    super(message);
  }

  public InvalidInputException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidInputException(Throwable cause) {
    super(cause);
  }
}
