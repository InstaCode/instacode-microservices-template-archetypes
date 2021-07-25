#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ${package}.spring.cloud.discovery.DiscoveryApplication;

/**
 * 
 * This Live Test requires:
 * * A Redis instance running in port 6379 (e.g. using `docker run --name some-redis -p 6379:6379 -d redis`)
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DiscoveryApplication.class)
public class SpringContextLiveTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}
