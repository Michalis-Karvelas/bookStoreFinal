package com.groupproject.services;


import com.groupproject.entities.Author;
import com.groupproject.repository.AuthorRepository;
import com.groupproject.requests.AuthorRequest;
import com.groupproject.services.interfaces.IAuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;


@Slf4j
@Service
public class AuthorServiceImpl implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }


    @Override
    public boolean newAuthor(AuthorRequest request) {
        log.info("Ready to insert a new Author");
        Author author=new Author(request.getFirstName(), request.getLastName(), request.getCountry());
        Author newAuthor=authorRepository.save(author);
        log.info("The new author is {}",newAuthor);
        log.info("The author has been inserted to the DB");
        return true;
    }

    @Override
    public Author updateAuthor(Long id, AuthorRequest request) {
        log.info("Ready to update an existing author");
        Author existingAuthor = authorRepository.findById(id).orElse(null);
        if (isNull(existingAuthor)) {
            log.info("The author does not exists");
            return null;
        }
        existingAuthor.setFirstName(request.getFirstName());
        existingAuthor.setLastName(request.getLastName());
        existingAuthor.setCountry(request.getCountry());
        Author updatedAuthor= authorRepository.save(existingAuthor);
        log.info("The updated author is {}", updatedAuthor);
        log.info("The updated author has been inserted to the DB");
        return updatedAuthor;
    }

    //???
    @Override
    public boolean deleteAuthor(Long id) {
        log.info("Ready to delete an author");
        if (authorRepository.existsById(id)){
            authorRepository.deleteById(id);
            log.info("author deleted successfully");
            return true;
        }
        log.info("author has not deleted successfully");
        return false;
    }
}
