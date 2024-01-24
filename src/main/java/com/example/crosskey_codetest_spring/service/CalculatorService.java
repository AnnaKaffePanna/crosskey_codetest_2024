package com.example.crosskey_codetest_spring.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public double roundUp(final double number){
        return (int)((number * 10.0) + 0.5) / 10.0;
    }

    public static double ownPow(final double base, final int exponent) {
        double ans = 1; // if the exponent is 0 one will be the default answer

        if (exponent != 0) {
            // checks IF the exponent is positive, ELSE it is negative and does the correct calculation based on this
            int absExponent = exponent > 0 ? exponent : (-1) * exponent;
            for (int i = 1; i <= absExponent; i++) {
                ans *= base;
            }

            if (exponent < 0) {
                // Negative exponents are inverted
                ans = 1.0 / ans;
            }
        }
        return ans;
    }

    public double calculateMonthlyRate(final double yearlyInterest, final int months, final double loan){
        if(yearlyInterest <= 0 || months < 12 || loan <= 0){
            return 0.0;
        }

        double interestOnMonthlyBasis = calculateMonthlyInterest(yearlyInterest, months);

        return loan * (
                (interestOnMonthlyBasis * (ownPow(1+interestOnMonthlyBasis, months)) /
                        (ownPow(1+interestOnMonthlyBasis, months) -1)));
    }


    public double calculateMonthlyInterest(final double yearlyInterest, final int months){
        if(yearlyInterest < 0 || months < 12){
            return 0.0;
        }
        return (yearlyInterest/100)/months;
    }

    public int yearsIntoMonths(final int years) {
        int months = 0;

        for (int i = 0; i < years; i++) {
            months += 12;
        }

        return months;
    }

    public int monthsIntoYears(final int months) {
        return months/12;
    }

}

