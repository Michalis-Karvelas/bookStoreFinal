package com.groupproject.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long orderId;

    @Column(name="order_date")
    private LocalDateTime orderDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="account_id",nullable=false)
    private Account account;

    @Column(name="total_coins")
    private double totalCoins;

    @ToString.Exclude
    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private Set<OrderDetails> orderDetails;

    public Order(LocalDateTime orderDate, Account account, double totalCoins) {
        this.orderDate = orderDate;
        this.account = account;
        this.totalCoins = totalCoins;
    }

    public Order(LocalDateTime orderDate, Account account, double totalCoins, Set<OrderDetails> orderDetails) { //LocalDateTime
        this.orderDate = orderDate;
        this.account = account;
        this.totalCoins = totalCoins;
        this.orderDetails = orderDetails;
    }

}
