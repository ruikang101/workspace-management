package tcss556.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tcss556.filters.JWTFilter;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Resource
    private JWTFilter filter;

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public InetAddressValidator validator(){
        return InetAddressValidator.getInstance();
    }

    @Bean
    public DateFormat getDataFormat(){
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(filter).addPathPatterns("/rooms/*", "/users/*", "/meetings/*");
    }
}
