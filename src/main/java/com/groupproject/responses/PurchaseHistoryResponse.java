package com.groupproject.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groupproject.entities.Publisher;
import com.groupproject.entities.PurchaseHistory;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseHistoryResponse extends Response{

    private List<PurchaseHistory> purchaseHistories;
    private PurchaseHistory purchaseHistory;

    public PurchaseHistoryResponse(String msg, List<PurchaseHistory> purchaseHistories){
        super(msg);
        this.purchaseHistories=purchaseHistories;
    }

    public PurchaseHistoryResponse(String msg, PurchaseHistory purchaseHistory){
        super(msg);
        this.purchaseHistory=purchaseHistory;
    }
}
