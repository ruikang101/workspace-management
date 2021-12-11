package tcss556.utils.auth;

import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Data;

/** Validation result entity class. */
@Data
@Builder
public class ValidationResult {
  private Claims claims;
  private Throwable error;
}
