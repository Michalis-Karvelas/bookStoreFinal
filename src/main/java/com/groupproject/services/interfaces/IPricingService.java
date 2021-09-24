package com.groupproject.services.interfaces;

import com.groupproject.entities.Pricing;
import com.groupproject.requests.PricingRequest;

import java.util.List;

public interface IPricingService {

    // get the list of pricing
    List<Pricing> getAll();

    // get a price (by id)
    Pricing getPriceById(Long id);

    // add a new price(by id)
    boolean newPrice( PricingRequest request);

    // update a price with id
    Pricing updatePrice(Long id, PricingRequest request);

    // delete a price(by id)
    boolean deletePrice(Long id);
}
