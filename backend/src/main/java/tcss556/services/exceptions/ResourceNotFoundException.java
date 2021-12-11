package tcss556.services.exceptions;
/** InternalException is used to indicate error related to resource not found */
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException() {
    super();
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String resource, long id) {
    super(String.format("%s:%s", resource, id));
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ResourceNotFoundException(Throwable cause) {
    super(cause);
  }
}
