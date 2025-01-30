package io.nqa.test.bankaccounthandling.service;

import io.nqa.test.bankaccounthandling.configuration.ApplicationProperties;

public interface ICurrencyService {
    ApplicationProperties.CurrencyValue getCurrencyValue(ApplicationProperties.Currency currency);
    double convertCurrency(ApplicationProperties.Currency fromCurrency, ApplicationProperties.Currency toCurrency, double amount);
}
