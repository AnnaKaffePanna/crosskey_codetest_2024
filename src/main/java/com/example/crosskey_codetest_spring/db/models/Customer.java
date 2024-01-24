package com.example.crosskey_codetest_spring.db.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double loan;
    private double yearlyInterest;
    private Integer years;
    private double monthlyRate;
    public Customer(final String name, final double loan, final double yearlyInterest, final Integer years, double monthlyRate) {
        this.name = name;
        this.loan = loan;
        this.yearlyInterest = yearlyInterest;
        this.years = years;
        this.monthlyRate = monthlyRate;
    }
}
