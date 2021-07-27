#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId};

public interface OrderClient {

    OrderResponse order(OrderDTO orderDTO);
}
