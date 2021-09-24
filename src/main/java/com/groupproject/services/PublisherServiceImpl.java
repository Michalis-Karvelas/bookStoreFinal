package com.groupproject.services;


import com.groupproject.entities.Publisher;
import com.groupproject.repository.PublisherRepository;
import com.groupproject.requests.PublisherRequest;
import com.groupproject.services.interfaces.IPublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class PublisherServiceImpl implements IPublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public List<Publisher> getAll() {
        log.info("Ready to find all the publishers");
        return publisherRepository.findAll();
    }

    @Override
    public Publisher getById(Long id) {
        log.info("Ready to find the publisher with the id {}",id);
        return publisherRepository.findById(id).orElse(null);
    }

    @Override
    public void addPublisher(PublisherRequest request) {
        log.info("Ready to create a new Publisher");
        Publisher publisher=new Publisher(request.getName(), request.getCountry(), request.getCity());
        Publisher newPublisher=publisherRepository.save(publisher);
        log.info("The new Publisher has been inserted to the db");
    }

    @Override
    public Publisher updatePublisher(Long id, PublisherRequest request) {
        log.info("Ready to find the Publisher with the id {}",id);
        Publisher existingPublisher=publisherRepository.findById(id).orElse(null);
        if (isNull(existingPublisher)){
            log.info("The Publisher does not exists");
        }
        log.info("Ready to update this Publisher");
        existingPublisher.setName(request.getName());
        existingPublisher.setCountry(request.getCountry());
        existingPublisher.setCity(request.getCity());
        Publisher updatedPublisher=publisherRepository.save(existingPublisher);
        log.info("The updated Publisher has been saved to the DB");
        return updatedPublisher;
    }

    @Override
    public boolean deletePublisher(Long id) {
        log.info("Ready to delete the publisher with the id {}",id);
        if (publisherRepository.existsById(id)){
            publisherRepository.deleteById(id);
            log.info("publisher deleted successfully");
            return true;
        }
        log.info("publisher has not deleted successfully");
        return false;
    }
}
