package com.example.crosskey_codetest_spring.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {
    CalculatorService calculatorService = new CalculatorService();

    /**
     * Tests for calculateMonthlyInterest method
     */

    @Test
    void fivePercentYearlyInterestRateIntoMonthlyInterest(){
        assertEquals(0.004166666666666667, calculatorService.calculateMonthlyInterest(5, 12));
    }
    @Test
    void monthlyInterestZeroTest(){
        assertEquals(0.0, calculatorService.calculateMonthlyInterest(0, 0));
    }

    @Test
    void monthlyInterestNegativeValueTest(){
        assertEquals(0.0, calculatorService.calculateMonthlyInterest(-1, -1));
    }

    @Test
    void zeroTestOnlyInterest(){
        assertEquals(0.0, calculatorService.calculateMonthlyInterest(0, 12));
    }
    @Test
    void zeroTestOnlyMonths(){
        assertEquals(0.0, calculatorService.calculateMonthlyInterest(5, 0));
    }

    @Test
    void negativeValueTestOnlyInterest(){
        assertEquals(0.0, calculatorService.calculateMonthlyInterest(-1, 12));
    }

    @Test
    void negativeValueTestOnlyMonths(){
        assertEquals(0.0, calculatorService.calculateMonthlyInterest(5, -1));
    }

    /**
     * Tests for calculateMonthlyRate method
     */

    @Test
    void calculateMonthlyRate(){
        assertEquals(85.60748178846744, calculatorService.calculateMonthlyRate(5, 12, 1000));
    }

    @Test
    void calculateRateZeroTest(){
        assertEquals(0.0, calculatorService.calculateMonthlyRate(0, 0, 0));
    }

    @Test
    void calculateRateNegativeValuesTest(){
        assertEquals(0.0, calculatorService.calculateMonthlyRate(-1, -1, -1));
    }

    @Test
    void calculateRateWhenInterestIsZero(){
        assertEquals(0.0, calculatorService.calculateMonthlyRate(0, 12, 1000));
    }

    /**
     * Tests for roundUp method
     */

    @Test
    void roundUpToOne(){
        assertEquals(1.0, calculatorService.roundUp(0.95));
    }

    @Test
    void noRoundUp(){
        assertEquals(0.9, calculatorService.roundUp(0.94));
    }

    @Test
    void roundUpZeroTest(){
        assertEquals(0, calculatorService.roundUp(0.00));
    }

    /**
     * Tests for ownPow method
     */

    @Test
    void baseTwoAndExponentTwoEqualsFour(){
        assertEquals(Math.pow(2,2), CalculatorService.ownPow(2,2)); //Answer is 4
    }

    @Test
    void negativeBaseTwoAndExponentTwoEqualsPositiveFour(){
        assertEquals(Math.pow(-2,2), CalculatorService.ownPow(-2, 2)); //Answer is 4
    }

    @Test
    void negativeBasTwoAndExponentThreeEqualsNegativeEight(){
        assertEquals(Math.pow(-2, 3), CalculatorService.ownPow(-2, 3)); //Answer is -8
    }

    @Test
    void negativeBaseAndNegativeExponent() {
        assertEquals(Math.pow(-2, -2), CalculatorService.ownPow(-2, -2)); //Answer is -0.25
    }

    @Test
    void ownPowZeroTest(){
        assertEquals(Math.pow(0,0), CalculatorService.ownPow(0,0));
    }

    @Test
    void onlyExponentIsZeroEqualsOne(){
        assertEquals(Math.pow(2,0), CalculatorService.ownPow(2,0)); //Answer is 1
    }

    /**
     * Testcases for methods handling exchanges from months into year/years and vice versa
     */

    @Test
    void oneMonthIntoYears(){
        assertEquals(1, calculatorService.monthsIntoYears(12));
    }
    @Test
    void oneHundredAndEightyMonthsIntoYears(){
        assertEquals(15, calculatorService.monthsIntoYears(180));
    }

    @Test
    void monthsIntoYearsZeroTest(){
        assertEquals(0, calculatorService.yearsIntoMonths(0));
    }
    @Test
    void monthsIntoYearsNegativeValueTest(){
        assertEquals(0, calculatorService.yearsIntoMonths(-1));
    }

    @Test
    void valueUnder12Months(){
        assertEquals(0, calculatorService.monthsIntoYears(1));
    }

}