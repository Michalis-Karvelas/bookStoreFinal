package com.groupproject.services;

import com.groupproject.entities.Pricing;
import com.groupproject.repository.PricingRepository;
import com.groupproject.requests.PricingRequest;
import com.groupproject.services.interfaces.IPricingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class PricingServiceImpl implements IPricingService {

    @Autowired
    private PricingRepository pricingRepository;


    @Override
    public List<Pricing> getAll() {
        log.info("Ready to find all the elements in pricing list");
        return pricingRepository.findAll();
    }

    @Override
    public Pricing getPriceById(Long id) {
        log.info("Ready to find the element with the id {} from the pricing list",id);
        return pricingRepository.findById(id).orElse(null);
    }

    @Override
    public boolean newPrice( PricingRequest request) {
        log.info("Ready to create a price. The request is {}",request);
        log.info("Ready to save the new price");
        Pricing pricing=new Pricing(request.getStartingPrice(), request.getDiscount(),
                request.getStartingDate(),request.getEndingDate());
        Pricing newPrice=pricingRepository.save(pricing);
        log.info("The new price is {}",newPrice);
        log.info("The price has been inserted to the DB");
        return true;
    }


    @Override
    public Pricing updatePrice(Long id, PricingRequest request) {
        log.info("Ready to update a price. The request is {}",request);
        Pricing existingPricing =pricingRepository.findById(id).orElse(null);
        existingPricing.setStartingPrice(request.getStartingPrice());
        existingPricing.setDiscount(request.getDiscount());
        existingPricing.setStartingDate(request.getStartingDate());
        existingPricing.setEndingDate(request.getEndingDate());
        Pricing updatedPricing= pricingRepository.save(existingPricing);
        log.info("The updated price has been inserted to the DB");
        return updatedPricing;
    }

    @Override
    public boolean deletePrice(Long id) {
        log.info("Ready to delete a price");
        if(isNull(id)) {
            log.info("There is no matching id in the DB");
            return false;
        }
        log.info("The Price with id {} was successfully deleted from the DB",id);
        pricingRepository.deleteById(id);
        return true;
    }
}
