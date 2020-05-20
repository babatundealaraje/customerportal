package com.babatunde.portal.service;

import java.util.Collection;

import com.babatunde.portal.domain.Account;
import com.babatunde.portal.domain.Customer;
import com.babatunde.portal.repository.CustomerRepository;
import com.babatunde.portal.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing customer.
 */
@Service
@Transactional
public class CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registerCustomer(Customer customer) {
        customer.setAccount(createCustomerAccount());

        final Customer savedCustomer = customerRepository.save(customer);
        log.debug("Created Information for Customer: {}", savedCustomer);
        return savedCustomer;
    }

    @Transactional(readOnly = true)
    public Collection<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer getCustomer(Long id) {
        return customerRepository.getOne(id);
    }

    private Account createCustomerAccount() {
        final Long accountID = (long) Math.round(Math.random() * 10000);
        log.info("Account ID created {}", accountID);
        return new Account(String.valueOf(accountID), Constants.INITIAL_POINT);
    }
}
