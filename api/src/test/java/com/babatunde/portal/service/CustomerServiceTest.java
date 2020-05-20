package com.babatunde.portal.service;

import com.babatunde.portal.PortalApplication;
import com.babatunde.portal.domain.Customer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortalApplication.class)
@ActiveProfiles(profiles = "test")
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void customerTest() {
        Customer customer = new Customer("Babatunde Alaraje");

        customer = customerService.registerCustomer(customer);
        Customer persistedResult = customerService.getCustomer(customer.getId());

        Assert.assertNotNull(persistedResult.getAccount());
        Assert.assertNotNull(persistedResult.getCreatedAt());
        Assert.assertNotNull(persistedResult.getLastModified());

    }
}