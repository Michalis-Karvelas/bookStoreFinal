package com.groupproject.requests;

import com.groupproject.entities.Account;
import com.groupproject.entities.OrderDetails;

import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

//    private Account account;
    private Long accountId;
    private double totalCoins;
//    private Set<OrderDetails> orderDetails;
//    private List<Long> orderDetailsIds;

//    public OrderRequest(double totalCoins) {
//        this.totalCoins = totalCoins;
//    }
}
