package com.babatunde.portal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "account_number", length = 50)
    private String accountNumber;

    @Column(name = "account_point")
    private Long points;

    @Column(name = "customer_transaction")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<CustomerTransaction> customerTransactions = new HashSet<>();

    public Account(String accountNumber, Long points) {
        this.accountNumber = accountNumber;
        this.points = points;
    }

    public void addTransaction(CustomerTransaction transaction) {
        customerTransactions.add(transaction);
    }

    public void removeTransaction(CustomerTransaction transaction) {
        customerTransactions.remove(transaction);
    }

}