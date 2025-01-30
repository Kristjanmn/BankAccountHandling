package io.nqa.test.bankaccounthandling.service;

import io.nqa.test.bankaccounthandling.configuration.ApplicationProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyServiceTest {
    @Autowired
    private ICurrencyService currencyService;

    @Test
    void getCurrencyValue() {
        ApplicationProperties.Currency currency = ApplicationProperties.Currency.SEK;
        double expectedValue = 975.67;
        double actualValue = currencyService.getCurrencyValue(currency).getValue();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void convertCurrency() {
        ApplicationProperties.Currency fromCurrency = ApplicationProperties.Currency.EUR;
        ApplicationProperties.Currency toCurrency = ApplicationProperties.Currency.RUB;
        double amount = 85.17;
        double expectedResult = 8756.33;
        double actualResult = currencyService.convertCurrency(fromCurrency, toCurrency, amount);
        assertEquals(expectedResult, actualResult);
    }
}