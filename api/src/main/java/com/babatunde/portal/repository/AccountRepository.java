package com.babatunde.portal.repository;

import com.babatunde.portal.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}