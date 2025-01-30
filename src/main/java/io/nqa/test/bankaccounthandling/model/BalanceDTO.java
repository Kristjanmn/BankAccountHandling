package io.nqa.test.bankaccounthandling.model;

import io.nqa.test.bankaccounthandling.configuration.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Object used to send balance for specific currency, to client.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceDTO {
    private ApplicationProperties.Currency currency;
    private Double amount;
}
