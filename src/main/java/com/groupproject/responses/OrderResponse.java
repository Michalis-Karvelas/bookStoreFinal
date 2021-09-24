package com.groupproject.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groupproject.entities.Order;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse extends Response {

    private List<Order> orders;
    private Order order;
    private Long orderId;

    public OrderResponse(String msg, List<Order> orders) {

        super(msg);
        this.orders = orders;
    }

    public OrderResponse(String msg, Order order) {

        super(msg);
        this.order = order;
    }
    public OrderResponse(String msg,Long orderId){
        super(msg);
        this.orderId=orderId;
    }
}
