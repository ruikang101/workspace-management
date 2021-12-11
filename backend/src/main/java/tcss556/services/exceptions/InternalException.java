package tcss556.services.exceptions;

/** InternalException is used to indicate error related to server itself. */
public class InternalException extends RuntimeException {
  public InternalException() {
    super();
  }

  public InternalException(String message) {
    super(message);
  }

  public InternalException(String message, Throwable cause) {
    super(message, cause);
  }

  public InternalException(Throwable cause) {
    super(cause);
  }
}
