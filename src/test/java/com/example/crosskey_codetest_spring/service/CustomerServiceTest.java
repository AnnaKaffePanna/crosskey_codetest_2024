package com.example.crosskey_codetest_spring.service;

import com.example.crosskey_codetest_spring.db.models.Customer;
import com.example.crosskey_codetest_spring.db.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private static List<Customer> customers;

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CalculatorService calculatorService;
    @Mock
    private ProspectFileService prospectFileService;

    @BeforeAll
    public static void setup() {
        Customer customer1 = new Customer("Mary Sue", 10.0, 10.0, 10, 10.0);
        Customer customer2 = new Customer("John Doe", 10.0, 10.0, 10, 10.0);
        customers = Arrays.asList(customer1, customer2);
    }

    @Test
    void processProspectsIntoDatabase() throws IOException {
        // Mocking
        when(prospectFileService.readFile("prospects.txt")).thenReturn(customers);

        // Call and verify method
        customerService.processProspectsIntoDatabase();
        verify(customerRepository).saveAll(customers);
    }

    @Test
    void getAllCustomers() {
        // Call and verify method
        customerService.getAllCustomers();
        verify(customerRepository).findAll();
    }

    @Test
    void isDatabaseEmpty() {
        // Call and verify method
        when(customerRepository.count()).thenReturn(0L);
        assertTrue(customerService.isDatabaseEmpty());
    }

    @Test
    void saveCustomer() {
        // Mocking
        double calculatedMonthlyRate = calculatorService.calculateMonthlyRate(10.0,10,10.0);
        when(calculatorService.calculateMonthlyRate(anyDouble(), anyInt(), anyDouble())).thenReturn(calculatedMonthlyRate);
        Customer expectedCustomer = new Customer("Jane Doe", 10.0,10.0,10, calculatorService.roundUp(calculatedMonthlyRate));

        // Call and verify method
        customerService.saveCustomer(expectedCustomer);
        verify(customerRepository).save(eq(expectedCustomer));
    }
}