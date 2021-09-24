package com.groupproject.services.interfaces;

import com.groupproject.entities.OrderDetails;
import com.groupproject.requests.OrderDetailsRequest;
import java.util.List;

public interface IOrderDetailsService {

    //list for all orders
    List<OrderDetails> getAll();

    // find orderDetails with id
    OrderDetails getOrderDetailsById(Long id);

    // find orderDetails by OrderId
    List<OrderDetails> getOrderDetailsByOrderId(Long orderId);

    //new order
    boolean createOrderDetails(OrderDetailsRequest request);

//    //update orderDetails with id
//    OrderDetails updateOrderDetails(Long id, OrderDetailsRequest request);

    //delete orderDetails with id
    boolean deleteById(Long id);
}

