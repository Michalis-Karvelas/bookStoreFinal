package com.groupproject.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groupproject.entities.OrderDetails;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailsResponse extends Response {

    private List<OrderDetails> orderDetailsList;
    private OrderDetails orderDetails;

    public OrderDetailsResponse(String msg, List<OrderDetails> orderDetailsList) {

        super(msg);
        this.orderDetailsList = orderDetailsList;
    }

    public OrderDetailsResponse(String msg, OrderDetails orderDetails) {

        super(msg);
        this.orderDetails = orderDetails;
    }
}
