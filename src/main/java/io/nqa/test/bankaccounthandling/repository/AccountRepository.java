package io.nqa.test.bankaccounthandling.repository;

import io.nqa.test.bankaccounthandling.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
}
