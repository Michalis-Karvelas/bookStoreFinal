package com.groupproject.repository;

import com.groupproject.entities.Book;
import com.groupproject.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //find the books by language
    List<Book> findByLanguages_LanguageType(String languageType);

    //find the books by categories
    List<Book> findByCategories_Type(String categoryType);

    //find the books by authorId
    List<Book> findByAuthors_AuthorId(Long authorId);

    //find book by order details
    List<Book> findByOrderDetails_Order_Account_AccountId(Long accountId);

    //find the books by orderId --testing
    List<Book> findByOrderDetails_Order_OrderId(Long orderId);

}
