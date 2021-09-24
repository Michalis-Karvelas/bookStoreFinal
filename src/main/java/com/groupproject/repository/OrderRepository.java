package com.groupproject.repository;

import com.groupproject.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // get orders by accountId
    List<Order> findOrderByAccount_AccountId(Long accountId);

}
