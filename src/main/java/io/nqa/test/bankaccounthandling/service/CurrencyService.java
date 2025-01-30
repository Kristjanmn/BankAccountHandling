package io.nqa.test.bankaccounthandling.service;

import io.nqa.test.bankaccounthandling.configuration.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyService implements ICurrencyService {
    private final ApplicationProperties applicationProperties;

    /**
     * Get currency value for specified currency.<br>
     * Currency value = amount of money one gram of gold is worth.
     *
     * @param currency currency value to get
     * @return currency value in gold
     */
    @Override
    public ApplicationProperties.CurrencyValue getCurrencyValue(ApplicationProperties.Currency currency) {
        for (ApplicationProperties.CurrencyValue cv : applicationProperties.getCurrencyValues())
            if (cv.getCurrency().equals(currency))
                return cv;
        return null;
    }

    /**
     * Convert specified amount of money from one currency to another and return its value.
     *
     * @param fromCurrency currency to convert from
     * @param toCurrency currency to convert to
     * @param amount amount of money to convert
     * @return converted currency amount
     */
    @Override
    public double convertCurrency(ApplicationProperties.Currency fromCurrency, ApplicationProperties.Currency toCurrency, double amount) {
        double gold = amount / getCurrencyValue(fromCurrency).getValue();
        return getCurrencyValue(toCurrency).getValue() * gold;
    }
}
