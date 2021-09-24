package com.groupproject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="account_purchase_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseHistory implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="purchase_id")
    private Long purchaseId;

    @Column(name="purchase_date")
    private LocalDateTime purchaseDate;

    //maybe needs first purchase date and update purchasedate?That is only  if we keep the update method
    @Column(name="euros_spent")
    private double eurosSpent;

    @Column(name="purchased_coins")
    private double purchasedCoins;

    @ManyToOne
    @JoinColumn(name="account_id",referencedColumnName="account_id")//,nullable=false)
//    @JsonIgnore
    private Account account;

    public PurchaseHistory(LocalDateTime purchaseDate, double eurosSpent, double purchasedCoins, Account account) {
        this.purchaseDate = purchaseDate;
        this.eurosSpent = eurosSpent;
        this.purchasedCoins = purchasedCoins;
        this.account = account;
    }
}
