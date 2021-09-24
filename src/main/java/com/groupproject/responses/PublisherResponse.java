package com.groupproject.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groupproject.entities.Category;
import com.groupproject.entities.Publisher;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublisherResponse extends Response{

    private List<Publisher> publishers;
    private Publisher publisher;

    public PublisherResponse(String msg, List<Publisher> publishers){
        super(msg);
        this.publishers=publishers;
    }

    public PublisherResponse(String msg, Publisher publisher){
        super(msg);
        this.publisher=publisher;
    }

}
