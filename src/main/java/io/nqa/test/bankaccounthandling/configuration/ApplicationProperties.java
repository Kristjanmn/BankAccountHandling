package io.nqa.test.bankaccounthandling.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    /** List of used currencies with price for one gram of gold */
    private CurrencyValue[] currencyValues;

    /**
     * Object containing currency and price for one gram of gold.<br>
     * Used to provide fixed rate in currency conversion.
     */
    @Data
    public static class CurrencyValue {
        /** @see Currency */
        private Currency currency;
        /** Price for one gram of gold */
        private double value;
    }

    /**
     * List of currencies used
     */
    public enum Currency {
        EUR, USD, SEK, RUB
    }
}
