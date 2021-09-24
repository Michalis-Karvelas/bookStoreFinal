package com.groupproject.services.interfaces;

import com.groupproject.receipts.Receipt;

public interface IReceiptService {

    Receipt getReceiptByOrderId(Long orderId);
}
