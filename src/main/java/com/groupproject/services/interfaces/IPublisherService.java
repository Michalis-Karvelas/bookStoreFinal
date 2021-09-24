package com.groupproject.services.interfaces;


import com.groupproject.entities.Publisher;
import com.groupproject.requests.PublisherRequest;

import java.util.List;

public interface IPublisherService {

    // list of all publishers
    List<Publisher> getAll();

    // publisher by id
    Publisher getById(Long id);

    // new publisher
    void addPublisher(PublisherRequest request);

    // update publisher by id
    Publisher updatePublisher(Long id,PublisherRequest request);

    // delete publisher by id
    boolean deletePublisher(Long id);
}
