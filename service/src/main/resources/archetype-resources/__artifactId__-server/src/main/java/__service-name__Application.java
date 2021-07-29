#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot application starter class
 */
@SpringBootApplication
public class ${service-name}Application {
    public static void main(String[] args) {
        SpringApplication.run(${service-name}Application.class, args);
    }

}
