package com.groupproject.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PricingRequest {

    private double startingPrice;
    private double discount;
    private Date startingDate;
    private Date endingDate;

    public PricingRequest(double startingPrice, double discount, Date startingDate, Date endingDate) {
        this.startingPrice = startingPrice;
        this.discount = discount;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }
}
