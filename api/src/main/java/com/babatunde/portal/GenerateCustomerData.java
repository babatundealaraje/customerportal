package com.babatunde.portal;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.babatunde.portal.domain.Account;
import com.babatunde.portal.domain.Customer;
import com.babatunde.portal.domain.CustomerTransaction;
import com.babatunde.portal.repository.AccountRepository;
import com.babatunde.portal.service.CustomerService;
import com.babatunde.portal.util.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Component class for generating default random customer data.
 */
@Component
public class GenerateCustomerData implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {

        // create customers
        final Customer john = new Customer("Jonh Done");
        final Customer smith = new Customer("Smith Jane");
        final Customer babatunde = new Customer("Babatunde Alaraje");
        Stream.of(john, smith, babatunde).forEach(customerService::registerCustomer);

        customerService.getAllCustomers().stream().forEach(customer -> {
            IntStream.rangeClosed(0, 39).forEach((id) -> {
                final CustomerTransaction transaction = generateNewTransaction();
                transaction.setPoints(calculateTransactionPoints(transaction.getAmount()));
                final Account account = customer.getAccount();
                account.addTransaction(transaction);
                account.setPoints(calculateAccountPoints(customer.getAccount().getPoints(), transaction.getPoints()));

            });
            accountRepository.save(customer.getAccount());
        });
    }

    private long calculateAccountPoints(long points, long transactionPoint) {
        return points + transactionPoint;
    }

    private long calculateTransactionPoints(int amount) {
        final int applicableTwoPointsRoyalty = amount - Constants.TWO_POINT_ROYALTY;
        final int applicableOnePointRoyalty = amount - Constants.ONE_POINT_ROYALTY;

        int points = 0;
        if (applicableTwoPointsRoyalty >= 0) {
            points += (2 * applicableTwoPointsRoyalty);
        }

        if (applicableOnePointRoyalty > 0 && (applicableOnePointRoyalty / Constants.ONE_POINT_ROYALTY) > 0) {
            final int onePointRoyalty = Constants.ONE_POINT_ROYALTY
                    * (applicableOnePointRoyalty / Constants.ONE_POINT_ROYALTY);
            points += onePointRoyalty;
        }
        return points;
    }

    private int getRandomAmount() {
        return (int) Math.round(Math.random() * Constants.MAX_TRANSACTION_AMOUNT);
    }

    private CustomerTransaction generateNewTransaction() {
        return new CustomerTransaction(getRandomAmount(), randomDate());
    }

    private LocalDate randomDate() {
        final LocalDate from = LocalDate.of(2020, 1, 1);
        final LocalDate to = LocalDate.of(2020, 3, 31);
        final long randomEpochDay = ThreadLocalRandom.current().longs(from.toEpochDay(), to.toEpochDay()).findAny()
                .getAsLong();
        return LocalDate.ofEpochDay(randomEpochDay);
    }
}