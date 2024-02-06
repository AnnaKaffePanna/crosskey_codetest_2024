package com.example.crosskey_codetest_spring.service;

import com.example.crosskey_codetest_spring.db.models.Customer;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProspectFileService {
    private final CalculatorService calculatorService;

    public ProspectFileService(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    /** Reads prospects.txt and returns a List of Customers **/
    public List<Customer> readFile() throws IOException {
        List<Customer> customers = new ArrayList<>();

        int count = 0;

        InputStream is = getClass().getClassLoader().getResourceAsStream("prospects.txt");
        assert is != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = reader.readLine()) != null) {
            if(count != 0  && !line.isEmpty() && !line.equals(".")) {
                String lineWithNoBrackets = line.replaceAll("\"", "");
                handleLine(lineWithNoBrackets, customers);
            }
            count ++;
        }
        return customers;
    }

    /** Checks what kind of data is being read **/
    private  void handleLine(String line, List<Customer> customers){
        String[] lineArray = line.split(",");

        for(int i = 0; i < lineArray.length; i++){
            if(!Character.isDigit(lineArray[i].charAt(i))){
                if(!Character.isDigit(lineArray[i+1].charAt(i))){
                    lineArray[i] = lineArray[i] + " " + lineArray[i+1];
                    for(int j = i+1; j < lineArray.length; j++){
                        if(j != 4){
                            lineArray[j]= lineArray[j+1];
                        }
                    }
                    lineArray[lineArray.length-1] = null;

                }
                insertCustomerIntoList(lineArray, customers);
                return;
            }
        }
    }

    /** Creates one customer and inserts it into the list to be returned **/
    private void insertCustomerIntoList(String[] lineArray, List<Customer> customers){
        double loan = Double.parseDouble(lineArray[1]);
        double yearlyInterest = Double.parseDouble(lineArray[2]);
        int years = Integer.parseInt(lineArray[3]);
        int months = calculatorService.yearsIntoMonths(years);
        double monthlyRate = calculatorService.roundUp(calculatorService.calculateMonthlyRate(yearlyInterest,months,loan));


        Customer customer = new Customer(lineArray[0], loan, yearlyInterest, years, monthlyRate);
        System.out.println(customer);
        customers.add(customer);
    }
}
