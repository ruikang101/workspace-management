package tcss556.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public InetAddressValidator validator(){
        return InetAddressValidator.getInstance();
    }
}
