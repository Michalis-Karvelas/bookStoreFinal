package com.groupproject.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long categoryId;

    @Column(name="type")
    private String type;

    @ManyToMany(mappedBy="categories")
    @JsonIgnore
    private Set<Book> books;

    public Category(String type, Set<Book> books) {
        this.type = type;
        this.books = books;
    }

    public Category(String type) {

        this.type = type;
    }
}
