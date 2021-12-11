package tcss556.filters;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import tcss556.utils.auth.JWTUtil;
import tcss556.utils.auth.ValidationResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to server as a JWT authorization filter. All the requests except for create
 * user request, get weather request and user login request require a valid JWT otherwise will be
 * rejected.
 */
@Slf4j
@Component
public class JWTFilter implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if ("/users/".equalsIgnoreCase(request.getRequestURI())
        && "post".equalsIgnoreCase(request.getMethod())) {
      log.info("skip on create user api");
      return true;
    }
    String token = request.getHeader("x-authorization-token");
    if (StringUtils.isBlank(token)) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("msg", "User must login first!");
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.setStatus(HttpStatus.FORBIDDEN.value());
      response.getOutputStream().write(jsonObject.toString().getBytes());
      return false;
    }
    ValidationResult result = JWTUtil.validateToken(token);
    if (result.getError() == null) {
      return true;
    } else {
      response.setStatus(HttpStatus.FORBIDDEN.value());
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      JSONObject object = new JSONObject();
      if (result.getError() instanceof ExpiredJwtException) {
        object.put("msg", "Token is expired! Please login again!");
      } else {
        object.put("msg", "Invalid token!");
      }
      response.getOutputStream().write(object.toString().getBytes());
      return false;
    }
  }
}
