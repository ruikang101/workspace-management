package tcss556.utils.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tcss556.constants.AppConstants;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.UUID;

/** This class has all the utility methods used to generate and validate JWT token. */
public class JWTUtil {
  private static final SecretKey secretKey =
      new SecretKeySpec(AppConstants.JWT_KEY.getBytes(), "AES");

  /**
   * Create JWT Token for user.
   *
   * @param subject the owner of the token
   * @param ttlMillis ttl
   * @return String token.
   */
  public static String createJWT(String subject, Long ttlMillis) {
    long now = System.currentTimeMillis();
    Date date = new Date(now);
    JwtBuilder builder =
        Jwts.builder()
            .setId(UUID.randomUUID().toString())
            .setSubject(subject)
            .setIssuer("admin")
            .setIssuedAt(date)
            .signWith(SignatureAlgorithm.HS512, secretKey);
    if (ttlMillis > 0) {
      builder.setExpiration(new Date(now + ttlMillis));
    }
    return builder.compact();
  }

  /**
   * Valid the token.
   *
   * @param token from request.
   * @return ValidateResult including user info and validation result.
   */
  public static ValidationResult validateToken(String token) {
    ValidationResult.ValidationResultBuilder builder = ValidationResult.builder();
    try {
      Claims claims = extractClaims(token);
      builder.claims(claims);
    } catch (Exception e) {
      builder.error(e);
    }
    return builder.build();
  }

  /**
   * Get claims from token
   *
   * @param token request token
   * @return JWT Claims.
   */
  public static Claims extractClaims(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
  }
}
