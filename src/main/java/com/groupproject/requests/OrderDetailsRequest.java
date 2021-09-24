package com.groupproject.requests;

import com.groupproject.entities.Book;
import com.groupproject.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsRequest {

    private Long orderId;
    private Long bookId;
    private double originalPrice;
    private double discountRate;
    private double totalPrice;
}
