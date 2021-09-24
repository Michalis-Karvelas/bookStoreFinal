package com.groupproject.services.interfaces;

import com.groupproject.entities.Book;
import com.groupproject.requests.BookRequest;

import java.util.List;

public interface IBookService {

    // get the list for all books
    List<Book> getAll();

    //get a book by id
    Book getBookById(Long id);

    //get books by Language
    List<Book> getBookByLanguages(String languageType);

    //get books by categories
    List<Book> getBookByCategories(String categoryType);

    //get books by authorId
    List<Book> getBookByAuthorId(Long authorId);

    //get books by orderId -- testing
    List<Book> getBooksByOrderId(Long orderId);

    // create new book
    void createNewBook(BookRequest request);

    // update an existing book
    Book updateBook(Long id,BookRequest request);

    // delete a book from id
    boolean deleteBookById(Long id);

    int checkIfBookIsAlreadyBoughtByAccountId(Long bookId, Long accountId);

}
