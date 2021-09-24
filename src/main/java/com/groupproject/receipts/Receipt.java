package com.groupproject.receipts;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class Receipt {

    private String lastname;
    private String firstname;
    private Long orderId;
    private List<String> bookTitles;
    private List<Double> originalPrice;
    private List<Double> discount;
    private List<Double> totalPrices;
    private LocalDateTime orderDate;
    private Double totalCoins;

    public Receipt(String lastname, String firstname, Long orderId, List<String> bookTitles, List<Double> originalPrice, List<Double> discount, List<Double> totalPrices, LocalDateTime orderDate, Double totalCoins) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.orderId = orderId;
        this.bookTitles = bookTitles;
        this.originalPrice = originalPrice;
        this.discount = discount;
        this.totalPrices = totalPrices;
        this.orderDate = orderDate;
        this.totalCoins = totalCoins;
    }
}
