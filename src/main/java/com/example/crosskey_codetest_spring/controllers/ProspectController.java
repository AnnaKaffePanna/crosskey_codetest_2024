package com.example.crosskey_codetest_spring.controllers;

import com.example.crosskey_codetest_spring.db.models.Customer;
import com.example.crosskey_codetest_spring.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class ProspectController {
    private final CustomerService customerService;

    public ProspectController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String showProspects(Model model) throws IOException {
        // Read prospect.txt if database is empty
        if (customerService.isDatabaseEmpty()) {
            customerService.processProspectsIntoDatabase();
        }

        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "prospects";
    }

    @GetMapping("/addProspect")
    public String addProspectForm() {
        return "addProspect";
    }

    @PostMapping("/saveProspect")
    public String saveProspect(@ModelAttribute Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/";
    }
}

