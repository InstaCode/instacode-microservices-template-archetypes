#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This live test requires:
 * Eureka server and Gateway application up and running
 * 
 * <br>
 * For more info:
 * https://www.baeldung.com/spring-cloud-netflix-eureka
 * https://www.baeldung.com/spring-cloud-gateway-pattern
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringContextLiveTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }

}
