package com.groupproject.requests;

import com.groupproject.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    private String title;
    private String pages;
    private Date publicationDate;
    private String description;
    private double rating;
    private String isbn13;
    private Long publisherId;
    private Long pricingId;

    //testing
    private List<Long> authorIds;
    private List<Long> categoryIds;
    private List<Long> languageIds;

    private Long accountId;
    private Long bookId;


}
