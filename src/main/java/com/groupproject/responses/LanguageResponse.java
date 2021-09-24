package com.groupproject.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groupproject.entities.Category;
import com.groupproject.entities.Language;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LanguageResponse extends Response{

    private List<Language> languages;
    private Language language;

    public LanguageResponse(String msg, List<Language> languages){

        super(msg);
        this.languages=languages;
    }

    public LanguageResponse(String msg, Language language){

        super(msg);
        this.language=language;
    }
}
