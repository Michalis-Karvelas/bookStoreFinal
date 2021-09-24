package com.groupproject.controllers;

import com.groupproject.entities.Order;
import com.groupproject.requests.OrderRequest;
import com.groupproject.responses.OrderResponse;
import com.groupproject.responses.Response;
import com.groupproject.services.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static java.util.Objects.isNull;


import static java.util.Objects.isNull;

@Slf4j
@CrossOrigin(origins = " * ", allowedHeaders = " * ")
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    //get all the orders
    @GetMapping(value = "/getall")
    public OrderResponse getall() {

        log.info("Ready to find all the orders");
        return new OrderResponse("Found the orders", orderServiceImpl.getAll());
    }

    // get order by id
    @GetMapping(value = "/getbyid/{id}")
    public OrderResponse getById(@PathVariable Long id) {

        log.info("Ready to find order by id");
        return new OrderResponse("Found the asked order", orderServiceImpl.getOrderById(id));
    }

    // get order by account id
    @GetMapping(value = "/getbyaccountid/{accountId}",produces="application/json")
    public OrderResponse getOrderByAccountId(@PathVariable Long accountId){
        log.info("Ready to find order by account id {}",accountId);
        if (isNull(orderServiceImpl.getOrdersByAccountId(accountId))){
            log.info("There is no order with this accountId");
            return new OrderResponse("There is no order with this accountId",orderServiceImpl.getOrdersByAccountId(accountId));
        }else{
            log.info("Found the orders with accountId {}",accountId);
            return new OrderResponse("Found the Order", orderServiceImpl.getOrdersByAccountId(accountId));
        }
    }

    //create new order
    @PostMapping(value = "/new", consumes = "application/json",
            produces = "application/json")
    public OrderResponse createNewOrder(@RequestBody OrderRequest request) {

        log.info("Ready to create a new Order");
        Long x=orderServiceImpl.createOrder(request);
        return new OrderResponse("The order has been saved",x);
    }

    // update order with id
    @PutMapping(value="/update/{id}",consumes = "application/json", produces = "application/json")
    public Response updateExistingOrder(@PathVariable(value = "id") Long id,
                                          @RequestBody OrderRequest request){
        log.info("ready to update an order");
        Order order=orderServiceImpl.updateOrder(id,request);
        if (isNull(order)) {
            return new Response("There is no such order");
        }
        return new Response("The order has been updated");
    }

    // delete order by id
    @DeleteMapping("/delete/{id}")
    public Response deleteOrder(@PathVariable Long id) {

        log.info("Ready to delete an order");
        orderServiceImpl.deleteById(id);
        return new Response("The order has been deleted");
    }
}
