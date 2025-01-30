package io.nqa.test.bankaccounthandling.model;

import io.nqa.test.bankaccounthandling.configuration.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

/**
 * Object sent by client for withdrawing money from account.
 */
@Getter
@ToString
@AllArgsConstructor
public class WithdrawDTO {
    private UUID accountId;
    private ApplicationProperties.Currency currency;
    private Double amount;
}
