package com.example.crosskey_codetest_spring.controllers;

import com.example.crosskey_codetest_spring.db.models.Customer;
import com.example.crosskey_codetest_spring.service.CustomerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProspectController.class)
class ProspectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private static List<Customer> customers;

    @BeforeAll
    public static void setup() {
        Customer customer1 = new Customer("Mary Sue", 10.0, 10.0, 10, 10.0);
        Customer customer2 = new Customer("John Doe", 10.0, 10.0, 10, 10.0);
        customers = Arrays.asList(customer1, customer2);
    }

    @Test
    void showProspects() throws Exception {
        when(customerService.isDatabaseEmpty()).thenReturn(false);
        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("customers", customers))
                .andExpect(view().name("prospects"));
    }

    @Test
    void addProspectForm() throws Exception {
        mockMvc.perform(get("/addProspect"))
                .andExpect(status().isOk())
                .andExpect(view().name("addProspect"));
    }

    @Test
    void saveProspect() throws Exception {
        Customer customer = new Customer("Mary Sue", 10.0,10.0,10,10.0);
        doNothing().when(customerService).saveCustomer(customer);

        mockMvc.perform(post("/saveProspect")
                        .flashAttr("customer", customer))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}