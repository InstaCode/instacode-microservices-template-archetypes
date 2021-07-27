#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId};

import org.springframework.boot.web.${artifactId}.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.${artifactId}.RestTemplate;


@Component
public class OrderClientImpl implements OrderClient {

    private RestTemplate restTemplate;

    public OrderClientImpl(RestTemplateBuilder builder) {

        this.restTemplate = builder.build();
    }

    @Override
    public OrderResponse order(OrderDTO orderDTO) {

        String ${parentArtifactId}Url = "http://localhost:8002/order-${parentArtifactId}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OrderDTO> request = new HttpEntity<>(orderDTO, headers);

        OrderResponse orderResponse = restTemplate.postForObject(${parentArtifactId}Url + "/create", request, OrderResponse.class);

        return orderResponse;

    }
}
