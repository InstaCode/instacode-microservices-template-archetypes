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
public class ${service-name}ClientImpl implements ${service-name}Client {

    private RestTemplate restTemplate;

    public ${service-name}ClientImpl(RestTemplateBuilder builder) {

        this.restTemplate = builder.build();
    }

    @Override
    public ${service-name}Response order(${service-name}DTO orderDTO) {

        String ${parentArtifactId}Url = "http://localhost:8002/order-${parentArtifactId}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<${service-name}DTO> request = new HttpEntity<>(orderDTO, headers);

        ${service-name}Response orderResponse = restTemplate.postForObject(${parentArtifactId}Url + "/create", request, ${service-name}Response.class);

        return orderResponse;

    }
}
