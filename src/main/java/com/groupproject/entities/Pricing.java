package com.groupproject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="pricing")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pricing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pricing_id")
    private Long pricingId;

    @Column(name="starting_price")
    private double startingPrice;

    @Column(name="discount")
    private double discount;

    @Column(name="starting_date")
    private Date startingDate;

    @Column(name="ending_date")
    private Date endingDate;

    @OneToOne(mappedBy = "pricing")
    @JsonIgnore
    private Book book;

    public Pricing(double startingPrice, double discount, Date startingDate, Date endingDate) {
        this.startingPrice = startingPrice;
        this.discount = discount;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Pricing pricing = (Pricing) o;
//
//        return pricingId.equals(pricing.pricingId);
//    }
//
//    @Override
//    public int hashCode() {
//        return pricingId.hashCode();
//    }
}
