package io.nqa.test.bankaccounthandling.model;

import io.nqa.test.bankaccounthandling.configuration.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

/**
 * Object sent by client to exchange money from one currency to another.
 */
@Getter
@ToString
@AllArgsConstructor
public class ExchangeDTO {
    private UUID accountId;
    private ApplicationProperties.Currency fromCurrency;
    private ApplicationProperties.Currency toCurrency;
    private Double amount;
}
