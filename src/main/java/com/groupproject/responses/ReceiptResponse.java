package com.groupproject.responses;

import com.groupproject.receipts.Receipt;
import lombok.Data;

@Data
public class ReceiptResponse extends Response {

    private Receipt receipt;

    public ReceiptResponse(Receipt receipt) {
        this.receipt = receipt;
    }

    public ReceiptResponse(String msg, Receipt receipt) {
        super(msg);
        this.receipt = receipt;
    }
}
