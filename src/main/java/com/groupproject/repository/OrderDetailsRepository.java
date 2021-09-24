package com.groupproject.repository;

import com.groupproject.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails , Long> {

    //find order details by orderId
    List<OrderDetails> findByOrder_OrderId(Long orderId);
}
