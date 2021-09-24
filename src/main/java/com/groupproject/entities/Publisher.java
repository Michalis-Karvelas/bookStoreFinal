package com.groupproject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="publishers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publisher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="publisher_id")
    private Long publisherId;

    @Column(name="name")
    private String name;

    @Column(name="country")
    private String country;

    @Column(name="city")
    private String city;

    @OneToMany(mappedBy = "publisher")
    @JsonIgnore
    private Set<Book> books;

    public Publisher(String name, String country, String city) {
        this.name = name;
        this.country = country;
        this.city = city;
    }
}
