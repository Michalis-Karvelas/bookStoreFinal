package com.groupproject.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groupproject.entities.Account;
import com.groupproject.entities.Pricing;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PricingResponse extends Response{

    private List<Pricing> pricingList;
    private Pricing pricing;

    public PricingResponse(String msg, List<Pricing> pricingList){
        super(msg);
        this.pricingList=pricingList;
    }

    public PricingResponse(String msg, Pricing pricing){
        super(msg);
        this.pricing=pricing;
    }
}
