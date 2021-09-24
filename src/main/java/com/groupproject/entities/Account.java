package com.groupproject.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "username", nullable = false,unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "coins")
    private double coins;

//    @OneToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name="role_id",referencedColumnName = "role_id")
//    private Role role;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ToString.Exclude
    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<Order> orders;

    @ToString.Exclude
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<PurchaseHistory> purchaseHistorySet;

    public Account(String username, String password, String firstName,
            String lastName, Date dateOfBirth, String email,
            String gender, double coins, Role role) {

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.coins = coins;
        this.email = email;
        this.gender = gender;
        this.role = role;
    }
    public Account(String username, String password, String firstName,
                   String lastName, Date dateOfBirth, String email,
                   String gender, double coins) {

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.coins = coins;
        this.email = email;
        this.gender = gender;
    }
    public Account(String username, String password, String firstName,
                   String lastName, Date dateOfBirth, String email,
                   String gender) {

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.gender = gender;
    }
}
