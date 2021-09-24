package com.groupproject.services;


import com.groupproject.entities.*;
import com.groupproject.repository.*;
import com.groupproject.requests.BookRequest;
import com.groupproject.services.interfaces.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private AccountRepository accountRepository;

    // list of books
    @Override
    public List<Book> getAll() {
        log.info("Ready to find all the books");
        return bookRepository.findAll();
    }

    //list of books from languages
    public List<Book> getBookByLanguages(String languageType) {
        log.info("Ready to find all the books by language");
        return bookRepository.findByLanguages_LanguageType(languageType);
    }

    //list of books from categories
    public List<Book> getBookByCategories(String categoryType) {
        log.info("Ready to find the books by category");
        return bookRepository.findByCategories_Type(categoryType);
    }

    //list of books from authorId
    public List<Book> getBookByAuthorId(Long authorId) {
        log.info("Ready to find the books by authorId");
        return bookRepository.findByAuthors_AuthorId(authorId);
    }

    //list of books by accountId
    public List<Book> getBooksByAccountId(Long accountId) {
        log.info("Ready to find all the books by accountId");
        return bookRepository.findByOrderDetails_Order_Account_AccountId(accountId);
    }

    //list of books by orderId
    public List<Book> getBooksByOrderId(Long orderId) {
        log.info("Ready to find all the books by orderId {}", orderId);
        return bookRepository.findByOrderDetails_Order_OrderId(orderId);
    }

    // get book by id
    @Override
    public Book getBookById(Long id) {
        log.info("Ready to find a Book by id");
        return bookRepository.findById(id).orElse(null);
    }

    // create a new book ??
    @Override
    public void createNewBook(BookRequest request) {
        log.info("Ready to create a new Book");

        log.info("Ready to find the price");
        Long pricingId = request.getPricingId();
        Pricing pricing = pricingRepository.findById(pricingId).orElse(null);
        log.info("The Price is {}", pricing);

        log.info("Ready to find the publisher");
        Long publisherId = request.getPublisherId();
        Publisher publisher = publisherRepository.findById(publisherId).orElse(null);
        log.info("The publisher is{}", publisher);

        //find the Authors and add them to a list
        List<Long> authorsIds = request.getAuthorIds();
        Set<Author> authorsSet = new HashSet<>();
        for (Long current : authorsIds) {
            Author author = authorRepository.findById(current).orElse(null);
            log.info("The author is {}", author);
            authorsSet.add(author);
        }
        //find the Categories and them to a list
        List<Long> categoriesIds = request.getCategoryIds();
        Set<Category> categorySet = new HashSet<>();
        for (Long current : categoriesIds) {
            Category category = categoryRepository.findById(current).orElse(null);
            log.info("The category is {}", category);
            categorySet.add(category);
        }
        //find the Languages and add them to a list
        List<Long> languagesIds = request.getLanguageIds();
        Set<Language> languageSet = new HashSet<>();
        for (Long current : languagesIds) {
            Language language = languageRepository.findById(current).orElse(null);
            log.info("The language is {}", language);
            languageSet.add(language);
        }

        log.info("Ready to save the new Book");
        Book book = new Book(request.getTitle(), request.getPages(),
                request.getPublicationDate(), request.getDescription(), request.getRating(),
                request.getIsbn13(), pricing, authorsSet, publisher, categorySet, languageSet);
        bookRepository.save(book);
        log.info("The book has been saved successfully");
    }

    //update
    @Override
    public Book updateBook(Long id, BookRequest request) {
        log.info("Ready to create a new Book");
        Long publisherId = request.getPublisherId();
        Publisher publisher = publisherRepository.findById(publisherId).orElse(null);

        //find the Authors and add them to a list
        List<Long> authorsIds = request.getAuthorIds();
        Set<Author> authorsSet = new HashSet<>();
        for (Long current : authorsIds) {
            Author author = authorRepository.findById(current).orElse(null);
            log.info("The author is {}", author);
            authorsSet.add(author);
        }
        //find the Categories and them to a list
        List<Long> categoriesIds = request.getCategoryIds();
        Set<Category> categorySet = new HashSet<>();
        for (Long current : categoriesIds) {
            Category category = categoryRepository.findById(current).orElse(null);
            log.info("The category is {}", category);
            categorySet.add(category);
        }
        //find the Languages and them to a list
        List<Long> languagesIds = request.getLanguageIds();
        Set<Language> languageSet = new HashSet<>();
        for (Long current : languagesIds) {
            Language language = languageRepository.findById(current).orElse(null);
            log.info("The language is {}", language);
            languageSet.add(language);
        }
        Book existingBook = bookRepository.findById(id).orElse(null);
        existingBook.setTitle(request.getTitle());
        existingBook.setPages(request.getPages());
        existingBook.setPublicationDate(request.getPublicationDate());
        existingBook.setDescription(request.getDescription());
        existingBook.setRating(request.getRating());
        existingBook.setIsbn13(request.getIsbn13());
//        existingBook.setPricing(pricing);
        existingBook.setAuthors(authorsSet);
        existingBook.setPublisher(publisher);
        existingBook.setCategories(categorySet);
        existingBook.setLanguages(languageSet);
        Book updatedBook = bookRepository.save(existingBook);
        log.info("The updated book is {}", updatedBook);
        log.info("The updated Book has been inserted to the DB");
        return updatedBook;
    }


    @Override
    public boolean deleteBookById(Long id) {
        log.info("Ready to delete a book");
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            log.info("book deleted successfully");
            return true;
        }
        log.info("book has not deleted successfully");
        return false;
    }

    @Override
    public int checkIfBookIsAlreadyBoughtByAccountId(Long accountId, Long bookId) {
        log.info("Ready to find if the book is already bought");
        if (bookRepository.existsById(bookId) && accountRepository.existsById(accountId)) {
            List<Book> checkingList = getBooksByAccountId(accountId);
            Book tempBook = getBookById(bookId);
            if (checkingList.contains(tempBook)) {
                log.info("Book is already in user's ownership");
                return 1;
            } else {
                log.info("Book is not owned by this user");
                return -1;
            }
        }
        log.info("account or book doesn't exist");
        return 0;// if book or account doesn't exist
    }

}
