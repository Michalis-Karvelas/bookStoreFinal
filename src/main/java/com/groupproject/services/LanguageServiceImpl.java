package com.groupproject.services;


import com.groupproject.entities.Language;
import com.groupproject.repository.BookRepository;
import com.groupproject.repository.LanguageRepository;
import com.groupproject.requests.LanguageRequest;
import com.groupproject.services.interfaces.ILanguageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class LanguageServiceImpl implements ILanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    //testing
    @Autowired
    private BookRepository bookRepository;


    @Override
    public List<Language> getAll() {
        log.info("Ready to find all the languages");
        return languageRepository.findAll();
    }

    @Override
    public Language getById(Long id) {
        log.info("Ready to find the language with the id {}",id);
        return languageRepository.findById(id).orElse(null);
    }

    @Override
    public void newLanguage(LanguageRequest request) {
        log.info("Ready to create a new language");
        log.info("Ready to save the new language");
        Language language=new Language(request.getLanguageType());
        languageRepository.save(language);
        log.info("saved successfully");
    }

    @Override
    public Language updateLanguage(Long id, LanguageRequest request) {
        log.info("Ready to update an existing language");
        Language existingLanguage = languageRepository.findById(id).orElse(null);
        if (isNull(existingLanguage)) {
            log.info("The language does not exists");
            return null;
        }
        existingLanguage.setLanguageType(request.getLanguageType());
        Language updatedLanguage = languageRepository.save(existingLanguage);
        log.info("The updated Language is {}", updatedLanguage);
        log.info("The updated Language has been inserted to the DB");
        return updatedLanguage;
        }


    @Override
    public boolean deleteLanguage(Long id) {
        log.info("Ready to delete a language");
        if (isNull(id)){
            log.info("There is no match for this id in the Database");
            return false;
        }
        log.info("Language with the id {} was deleted successfully",id);
        languageRepository.deleteById(id);
        return true;
    }
}
