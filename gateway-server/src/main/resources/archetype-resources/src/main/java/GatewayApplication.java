#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClientSpecification;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.metric.SpanMetricReporter;
import org.springframework.cloud.sleuth.zipkin.HttpZipkinSpanReporter;
import org.springframework.cloud.sleuth.zipkin.ZipkinProperties;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import zipkin.Span;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableFeignClients
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Autowired(required = false)
    private List<RibbonClientSpecification> configurations = new ArrayList<>();
    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private SpanMetricReporter spanMetricReporter;
    @Autowired
    private ZipkinProperties zipkinProperties;
    @Value("${symbol_dollar}{spring.sleuth.web.skipPattern}")
    private String skipPattern;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SpringClientFactory springClientFactory() {
        SpringClientFactory factory = new SpringClientFactory();
        factory.setConfigurations(this.configurations);
        return factory;
    }

    @Bean
    public ZipkinSpanReporter makeZipkinSpanReporter() {
        return new ZipkinSpanReporter() {
            private HttpZipkinSpanReporter delegate;
            private String baseUrl;

            @Override
            public void report(Span span) {
                InstanceInfo instance = eurekaClient.getNextServerFromEureka("zipkin", false);
                if (!(baseUrl != null && instance.getHomePageUrl().equals(baseUrl))) {
                    baseUrl = instance.getHomePageUrl();
                    delegate = new HttpZipkinSpanReporter(new RestTemplate(), baseUrl, zipkinProperties.getFlushInterval(), spanMetricReporter);
                    if (!span.name.matches(skipPattern)) delegate.report(span);
                }
                if (!span.name.matches(skipPattern)) delegate.report(span);
            }
        };
    }
}
