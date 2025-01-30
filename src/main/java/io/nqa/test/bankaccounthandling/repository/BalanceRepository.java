package io.nqa.test.bankaccounthandling.repository;

import io.nqa.test.bankaccounthandling.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, UUID> {
}
