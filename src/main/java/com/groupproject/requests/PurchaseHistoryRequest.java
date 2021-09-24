package com.groupproject.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHistoryRequest {

    private LocalDateTime purchaseDate=LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formatDateTime = purchaseDate.format(format);

    private double eurosSpent;

    private double purchasedCoins;

    private Long accountId;


}
