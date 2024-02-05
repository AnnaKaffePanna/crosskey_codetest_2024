package com.example.crosskey_codetest_spring.service;

import com.example.crosskey_codetest_spring.db.models.Customer;
import com.example.crosskey_codetest_spring.db.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service

public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ProspectFileService prospectFileService;
    private final CalculatorService calculatorService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CalculatorService calculatorService, ProspectFileService prospectFileService) {
        this.customerRepository = customerRepository;
        this.prospectFileService = prospectFileService;
        this.calculatorService = calculatorService;
    }

    public void processProspectsIntoDatabase() throws IOException {
        List<Customer> customers = prospectFileService.readFile("prospects.txt");
        customerRepository.saveAll(customers);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public boolean isDatabaseEmpty() {
        return customerRepository.count() == 0;
    }

    public void saveCustomer(Customer customer){
        double monthlyRate = calculatorService.calculateMonthlyRate(customer.getYearlyInterest(),calculatorService.yearsIntoMonths(customer.getYears()),customer.getLoan());
        Customer processedCustomer = new Customer(customer.getName(),customer.getLoan(),customer.getYearlyInterest(),customer.getYears(),calculatorService.roundUp(monthlyRate));
        customerRepository.save(processedCustomer);
    }
}
