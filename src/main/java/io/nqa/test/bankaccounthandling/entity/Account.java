package io.nqa.test.bankaccounthandling.entity;

import io.nqa.test.bankaccounthandling.configuration.ApplicationProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;
    @OneToMany
    @Column(nullable = false)
    private List<Balance> balances = new ArrayList<>();

    public Account() {
        for (ApplicationProperties.Currency currency : ApplicationProperties.Currency.values())
            balances.add(new Balance(currency));
    }

    public boolean addBalance(ApplicationProperties.Currency currency, double amount) {
        for (Balance balance : balances)
            if (balance.getCurrency().equals(currency)) {
                balance.setAmount(balance.getAmount() + amount);
                return true;
            }
        return false;
    }

    public boolean withdrawBalance(ApplicationProperties.Currency currency, double amount) {
        for (Balance balance : balances)
            if (balance.getCurrency().equals(currency)) {
                if (balance.getAmount() < amount)
                    return false;
                balance.setAmount(balance.getAmount() - amount);
                return true;
            }
        return false;
    }

    public boolean exchange(ApplicationProperties.Currency fromCurrency, ApplicationProperties.Currency toCurrency, double amount, double convertedAmount) {
        Balance fromBalance = null;
        Balance toBalance = null;
        for (Balance balance : balances) {
            if (balance.getCurrency().equals(fromCurrency))
                fromBalance = balance;
            if (balance.getCurrency().equals(toCurrency))
                toBalance = balance;
        }
        if (fromBalance == null || toBalance == null)
            return false;
        if (fromBalance.getAmount() < amount)
            return false;
        fromBalance.setAmount(fromBalance.getAmount() - amount);
        toBalance.setAmount(toBalance.getAmount() + convertedAmount);
        return true;
    }
}
