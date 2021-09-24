package com.groupproject.controllers;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.groupproject.requests.BookRequest;
import com.groupproject.responses.BookResponse;
import com.groupproject.responses.Response;
import com.groupproject.services.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Slf4j
@CrossOrigin(origins = " * ", allowedHeaders = " * ")
@RestController
@RequestMapping(value = "/api/book")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    // list of all the books
    @GetMapping(value = "/getall", produces = "application/json")
    public BookResponse getAllBooks() {
        log.info("Ready to find all the books");
        return new BookResponse("Found all the books", bookService.getAll());
    }

    // list of all the books by language
    @GetMapping(value = "/getbylanguage/{languageType}", produces = "application/json")
    public BookResponse getAllBooksByLanguage(@PathVariable String languageType) {
        log.info("Ready to find all the books by language {}", languageType);
        if (bookService.getBookByLanguages(languageType).size() == 0) {
            log.info("No matched books with the given language");
            return new BookResponse("There are no books with this language", bookService.getBookByLanguages(languageType));
        } else {
            log.info("Found all the books with this language");
            return new BookResponse("Found all the books", bookService.getBookByLanguages(languageType));
        }
    }

    //list of all the books by orderId --testing
    @GetMapping(value = "/getbyorderid/{orderId}", produces = "application/json")
    public BookResponse getAllBooksByOrderId(@PathVariable Long orderId) {
        log.info("Ready to find all the books by order with id {}", orderId);
        if (bookService.getBooksByOrderId(orderId).size() == 0) {
            log.info("No matched books with the given order id");
            return new BookResponse("No matched books with the given order id", bookService.getBooksByOrderId(orderId));
        } else {
            log.info("Found all the books with this order id {}", orderId);
            return new BookResponse("Found all the books with this order id", bookService.getBooksByOrderId(orderId));
        }
    }

    // list of all the books by category
    @GetMapping(value = "/getbycategory/{categoryType}", produces = "application/json")
    public BookResponse getAllBooksByCategory(@PathVariable String categoryType) {
        log.info("Ready to find all the books by category {}", categoryType);
        if (bookService.getBookByCategories(categoryType).size() == 0) {
            log.info("No matched books with the given category");
            return new BookResponse("There are no books with this category", bookService.getBookByCategories(categoryType));
        } else {
            log.info("Found all the books with this category");
            return new BookResponse("Found all the books", bookService.getBookByCategories(categoryType));
        }
    }

    //list of all books by accountId --testing
    @GetMapping(value = "/getbyaccountid/{accountId}", produces = "application/json")
    public BookResponse getAllBooksByAccountId(@PathVariable Long accountId) {
        log.info("Ready to find all the books by account with id {}", accountId);
        if (bookService.getBooksByAccountId(accountId).size() == 0) {
            log.info("Couldn't find any book with this account id {}", accountId);
            return new BookResponse("Couldn't find any book with this account id", bookService.getBooksByAccountId(accountId));
        } else {
            log.info("Found all the books connected to this account id");
            return new BookResponse("Found all the books", bookService.getBooksByAccountId(accountId));
        }
    }

    // list of all the books by authorId
    @GetMapping(value = "/getbyauthor/{authorId}", produces = "application/json")
    public BookResponse getAllBooksByAuthor(@PathVariable Long authorId) {
        log.info("Ready to find all the books by authorId {}", authorId);
        if (bookService.getBookByAuthorId(authorId).size() == 0) {
            log.info("No matched books with the given author id {}", authorId);
            return new BookResponse("There are no books with this author id", bookService.getBookByAuthorId(authorId));
        } else {
            log.info("Found all the books with the given author id {}", authorId);
            return new BookResponse("Found all the books", bookService.getBookByAuthorId(authorId));
        }
    }

    // get book by id
    @GetMapping(value = "/getbyid/{id}")
    public BookResponse getById(@PathVariable Long id) {
        log.info("Ready to find the book with the id {}", id);
        if (isNull(bookService.getBookById(id))) {
            log.info("There is no book with id {}", id);
            return new BookResponse("There is no book with this id", bookService.getBookById(id));
        } else {
            log.info("Found the book with id {}", id);
            return new BookResponse("Found the book", bookService.getBookById(id));
        }
    }

    // create a new book with post method
    @PostMapping(value = "/new", consumes = "application/json",
            produces = "application/json")
    public Response createNewBook(@RequestBody BookRequest request) {
        log.info("Ready to create a new book");
        bookService.createNewBook(request);
        return new Response("The Book has been saved");

    }

    // update a book with ID with put method
    @PutMapping(value = "/update/{id}", consumes = "application/json",
            produces = "application/json")
    public Response updateBook(@PathVariable Long id, @RequestBody BookRequest request) {
        log.info("Ready to update the book with the id {}", id);
        bookService.updateBook(id, request);
        return new Response("The Book has been updated");
    }

    // delete a book with ID with delete method
    @DeleteMapping(value = "/delete/{id}")
    public Response deleteBook(@PathVariable Long id) {
        log.info("Ready to delete the book with id {}", id);
        bookService.deleteBookById(id);
        return new Response("The Book has been deleted");
    }

    @PostMapping(value = "/checkownership", consumes = "application/json",
            produces = "application/json")
    public Response checkIfBookIsBough(@RequestBody BookRequest request) {
        log.info("Ready to check");
        int result = bookService.checkIfBookIsAlreadyBoughtByAccountId(request.getAccountId(), request.getBookId());
        if (result == 1) {
            return new Response("Already bought");
        } else if (result == -1) {
            return new Response("Not Bought");
        }
        return new Response("Invalid request info");
    }

}
