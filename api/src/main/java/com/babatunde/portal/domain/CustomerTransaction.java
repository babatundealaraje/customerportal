package com.babatunde.portal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTransaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "date")
    private LocalDate transactionDate;

    @Column(name = "transaction_point")
    private Long points;

    public CustomerTransaction(int amount, LocalDate transactionDate) {
        this.amount = amount;
        this.transactionDate = transactionDate;
    }
}