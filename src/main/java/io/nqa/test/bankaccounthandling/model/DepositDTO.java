package io.nqa.test.bankaccounthandling.model;

import io.nqa.test.bankaccounthandling.configuration.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

/**
 * Object sent by client for depositing money to account.
 */
@Getter
@ToString
@AllArgsConstructor
public class DepositDTO {
    private UUID accountId;
    private ApplicationProperties.Currency currency;
    private Double amount;
}
