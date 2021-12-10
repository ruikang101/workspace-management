package tcss556.utils.auth;

import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationResult {
  private Claims claims;
  private Throwable error;
}
