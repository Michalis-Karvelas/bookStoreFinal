package com.groupproject.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groupproject.entities.Author;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorResponse extends Response{

    private List<Author> authors;
    private Author author;

    public AuthorResponse(String msg, List<Author> authors){
        super(msg);
        this.authors=authors;
    }

    public AuthorResponse(String msg, Author author){
        super(msg);
        this.author=author;
    }
}
