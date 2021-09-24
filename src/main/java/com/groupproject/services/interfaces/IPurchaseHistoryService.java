package com.groupproject.services.interfaces;

import com.groupproject.entities.PurchaseHistory;
import com.groupproject.requests.PurchaseHistoryRequest;

import java.util.List;

public interface IPurchaseHistoryService {

    // list of all purchases
    List<PurchaseHistory> getAll();

    // purchase by id
    PurchaseHistory getById(Long id);

    // new purchase
    void newPurchase(PurchaseHistoryRequest request);

    // update purchase by id
    PurchaseHistory updatePurchase(Long id, PurchaseHistoryRequest request);

    // delete a purchase by id
    boolean deletePurchase(Long id);
}
