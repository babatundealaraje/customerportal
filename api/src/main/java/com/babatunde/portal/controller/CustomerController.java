package com.babatunde.portal.controller;

import java.util.Collection;

import com.babatunde.portal.domain.Customer;
import com.babatunde.portal.service.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         all customers.
     */
    @GetMapping
    public ResponseEntity<Collection<Customer>> getAllCustomers() {
        final Collection<Customer> customers = customerService.getAllCustomers();
        log.info("Request to get all customers Information. Count is : {}", customers.size());
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
