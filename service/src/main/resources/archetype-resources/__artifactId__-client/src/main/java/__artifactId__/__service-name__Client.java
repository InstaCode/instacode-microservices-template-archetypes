#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId};

public interface ${service-name}Client {

    ${service-name}Response order(${service-name}DTO orderDTO);
}
