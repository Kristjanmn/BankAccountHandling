package io.nqa.test.bankaccounthandling.service;

import io.nqa.commons.CustomResponse;
import io.nqa.test.bankaccounthandling.configuration.ApplicationProperties;
import io.nqa.test.bankaccounthandling.entity.Account;
import io.nqa.test.bankaccounthandling.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountServiceTest {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ICurrencyService currencyService;
    @Autowired
    private ApplicationProperties applicationProperties;

    /** EUR balance in data.sql */
    private final double eurBalance = 34.22;
    /** USD balance in data.sql */
    private final double usdBalance = 59.66;
    /** SEK balance in data.sql */
    private final double sekBalance = 53.64;
    /** RUB balance in data.sql */
    private final double rubBalance = 38.57;

    @Test
    void getAccounts() {
        List<Account> accounts = accountService.getAccounts();
        assertEquals(1, accounts.size());
        assertEquals(UUID.fromString("36ea0ece-4781-4586-b4bf-ae6cdd620d39"), accounts.get(0).getId());
    }

    @Test
    void getAccountDtos() {
        CustomResponse response = accountService.getAccountDtos();
        assertTrue(response.isSuccess());
        assertNotNull(response.getObject());
        List<AccountDTO> accounts = (List<AccountDTO>) response.getObject();
        assertEquals(1, accounts.size());
        assertEquals(UUID.fromString("36ea0ece-4781-4586-b4bf-ae6cdd620d39"), accounts.get(0).getId());
    }

    @Test
    void getAccountById() {
        UUID id = UUID.fromString("36ea0ece-4781-4586-b4bf-ae6cdd620d39");
        Account account = accountService.getAccountById(id);
        assertEquals(id, account.getId());
        assertNotNull(account.getBalances());
        assertEquals(4, account.getBalances().size());
    }

    @Test
    void getAccountDtoById_success() {
        UUID id = UUID.fromString("36ea0ece-4781-4586-b4bf-ae6cdd620d39");
        CustomResponse response = accountService.getAccountDtoById(id);
        assertTrue(response.isSuccess());
        assertNotNull(response.getObject());
        AccountDTO account = (AccountDTO) response.getObject();
        assertEquals(id, account.getId());
        assertNotNull(account.getBalances());
        assertEquals(4, account.getBalances().size());
    }

    /**
     * Test {@link IAccountService#getAccountDtoById(UUID)}, providing an invalid
     * ID to the service and thus not getting an account.
     */
    @Test
    void getAccountDtoById_failure() {
        UUID id = UUID.fromString("36ea0ece-4781-4586-b4bf-ae6cdd620d38");
        CustomResponse response = accountService.getAccountDtoById(id);
        assertFalse(response.isSuccess());
        assertNull(response.getObject());
    }

    @Test
    void createAccount() {
        CustomResponse response = accountService.createAccount();
        assertTrue(response.isSuccess());
        assertNotNull(response.getObject());
        AccountDTO account = (AccountDTO) response.getObject();
        assertNotNull(account.getId());
        assertNotNull(account.getBalances());
    }

    @Test
    void saveAccount() {
        Account account = new Account();
        assertNull(account.getId());
        // save account
        account = accountService.saveAccount(account);
        assertNotNull(account.getId());
        assertNotNull(account.getBalances());
    }

    @Test
    void deposit() {
        UUID id = UUID.fromString("36ea0ece-4781-4586-b4bf-ae6cdd620d39");
        ApplicationProperties.Currency currency = ApplicationProperties.Currency.EUR;
        double amount = 25.49;
        DepositDTO depositDTO = new DepositDTO(id, currency, amount);
        CustomResponse response = accountService.deposit(depositDTO);
        assertTrue(response.isSuccess());
        assertNotNull(response.getObject());
        AccountDTO account = (AccountDTO) response.getObject();
        assertEquals(id, account.getId());
        assertNotNull(account.getBalances());
        for (BalanceDTO balance : account.getBalances())
            if (balance.getCurrency().equals(currency))
                assertEquals(eurBalance + amount, balance.getAmount());
    }

    @Test
    void deposit_negative() {
        UUID id = UUID.fromString("36ea0ece-4781-4586-b4bf-ae6cdd620d39");
        ApplicationProperties.Currency currency = ApplicationProperties.Currency.EUR;
        double amount = -25.49;
        DepositDTO depositDTO = new DepositDTO(id, currency, amount);
        CustomResponse response = accountService.deposit(depositDTO);
        assertFalse(response.isSuccess());
        assertNull(response.getObject());
    }

    @Test
    void withdraw() {
        UUID id = UUID.fromString("36ea0ece-4781-4586-b4bf-ae6cdd620d39");
        ApplicationProperties.Currency currency = ApplicationProperties.Currency.RUB;
        double amount = 25.49;
        WithdrawDTO withdrawDTO = new WithdrawDTO(id, currency, amount);
        CustomResponse response = accountService.withdraw(withdrawDTO);
        assertTrue(response.isSuccess());
        assertNotNull(response.getObject());
        AccountDTO account = (AccountDTO) response.getObject();
        assertEquals(id, account.getId());
        assertNotNull(account.getBalances());
        for (BalanceDTO balance : account.getBalances())
            if (balance.getCurrency().equals(currency))
                assertEquals(rubBalance - amount, balance.getAmount());
    }

    @Test
    void withdraw_negative() {
        UUID id = UUID.fromString("36ea0ece-4781-4586-b4bf-ae6cdd620d39");
        ApplicationProperties.Currency currency = ApplicationProperties.Currency.RUB;
        double amount = -25.49;
        WithdrawDTO withdrawDTO = new WithdrawDTO(id, currency, amount);
        CustomResponse response = accountService.withdraw(withdrawDTO);
        assertFalse(response.isSuccess());
        assertNull(response.getObject());
    }

    @Test
    void withdraw_overdraft() {
        UUID id = UUID.fromString("36ea0ece-4781-4586-b4bf-ae6cdd620d39");
        ApplicationProperties.Currency currency = ApplicationProperties.Currency.RUB;
        double amount = 39.99;
        WithdrawDTO withdrawDTO = new WithdrawDTO(id, currency, amount);
        CustomResponse response = accountService.withdraw(withdrawDTO);
        assertFalse(response.isSuccess());
        assertNull(response.getObject());
    }

    @Test
    void exchange() {
        UUID id = UUID.fromString("36ea0ece-4781-4586-b4bf-ae6cdd620d39");
        ApplicationProperties.Currency fromCurrency = ApplicationProperties.Currency.USD;
        ApplicationProperties.Currency toCurrency = ApplicationProperties.Currency.SEK;
        double amount = 37.58;
        double exchangeAmount = currencyService.convertCurrency(fromCurrency, toCurrency, amount);
        ExchangeDTO exchangeDTO = new ExchangeDTO(id, fromCurrency, toCurrency, amount);
        CustomResponse response = accountService.exchange(exchangeDTO);
        assertTrue(response.isSuccess());
        assertNotNull(response.getObject());
        AccountDTO account = (AccountDTO) response.getObject();
        assertEquals(id, account.getId());
        assertNotNull(account.getBalances());
        for (BalanceDTO balance : account.getBalances()) {
            if (balance.getCurrency().equals(fromCurrency))
                assertEquals(usdBalance - amount, balance.getAmount());
            if (balance.getCurrency().equals(toCurrency))
                assertEquals(sekBalance + exchangeAmount, balance.getAmount());
        }
    }
}