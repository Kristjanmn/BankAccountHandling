package io.nqa.test.bankaccounthandling.service;

import io.nqa.commons.CustomResponse;
import io.nqa.test.bankaccounthandling.entity.Account;
import io.nqa.test.bankaccounthandling.model.AccountDTO;
import io.nqa.test.bankaccounthandling.model.DepositDTO;
import io.nqa.test.bankaccounthandling.model.ExchangeDTO;
import io.nqa.test.bankaccounthandling.model.WithdrawDTO;
import io.nqa.test.bankaccounthandling.repository.AccountRepository;
import io.nqa.test.bankaccounthandling.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;
    private final BalanceRepository balanceRepository;
    private final ICurrencyService currencyService;
    private final ModelMapper modelMapper = new ModelMapper();
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Get list of all accounts.
     *
     * @return List of accounts
     */
    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Get list of accounts for client. This is just for testing and
     * endpoints like this should not be present in any production build.
     *
     * @return response body with list of all accounts
     */
    @Override
    public CustomResponse getAccountDtos() {
        logger.info("Received request for all accounts");
        List<AccountDTO> dtos = new ArrayList<>();
        for (Account account : getAccounts())
            dtos.add(modelMapper.map(account, AccountDTO.class));
        return new CustomResponse(dtos);
    }

    /**
     * Get {@link Account} by ID.
     *
     * @param id account ID
     * @return Account or null
     */
    @Override
    public Account getAccountById(UUID id) {
        return accountRepository.findById(id).orElse(null);
    }

    /**
     * Get account by ID for client.
     *
     * @param id account ID
     * @return response body
     */
    @Override
    public CustomResponse getAccountDtoById(UUID id) {
        logger.info("Received request for account {}", id);
        Account account = getAccountById(id);
        return account != null ?
                new CustomResponse(modelMapper.map(account, AccountDTO.class)) :
                new CustomResponse(false, "Account not found");
    }

    /**
     * Create new account and send it to client.
     *
     * @return response body
     */
    @Override
    public CustomResponse createAccount() {
        Account account = saveAccount(new Account());
        logger.info("New account {} was created", account.getId());
        return new CustomResponse(modelMapper.map(account, AccountDTO.class));
    }

    /**
     * Save account to database.
     *
     * @param account account to save
     * @return saved account
     */
    @Override
    public Account saveAccount(Account account) {
        balanceRepository.saveAll(account.getBalances());
        account = accountRepository.save(account);
        logger.info("Account {} was saved", account.getId());
        return account;
    }

    /**
     * Deposit money into account.
     *
     * @param depositDTO incoming object containing account ID, currency and amount to deposit
     * @return response body
     */
    @Override
    public CustomResponse deposit(DepositDTO depositDTO) {
        // User should not be able to deposit zero or negative value
        if (depositDTO.getAmount() <= 0) {
            logger.info("Account {} attempted to deposit zero or less ({})", depositDTO.getAccountId(), depositDTO.getAmount());
            return new CustomResponse(false, "Deposit amount must be positive value");
        }
        Account account = getAccountById(depositDTO.getAccountId());
        boolean result = account.addBalance(depositDTO.getCurrency(), depositDTO.getAmount());
        if (result) {
            logger.info("Account {} deposited {} {}", depositDTO.getAccountId(), depositDTO.getAmount(), depositDTO.getCurrency());
            return new CustomResponse(modelMapper.map(saveAccount(account), AccountDTO.class));
        }
        logger.warn("Account {} failed to deposit {} {}", depositDTO.getAccountId(), depositDTO.getAmount(), depositDTO.getCurrency());
        return new CustomResponse(false, "Could not deposit");
    }

    /**
     * Withdraw money from account.
     *
     * @param withdrawDTO incoming request body
     * @return response body
     */
    @Override
    public CustomResponse withdraw(WithdrawDTO withdrawDTO) {
        // User should not be able to withdraw zero or negative value
        if (withdrawDTO.getAmount() <= 0) {
            logger.info("Account {} attempted to withdraw zero or less ({})", withdrawDTO.getAccountId(), withdrawDTO.getAmount());
            return new CustomResponse(false, "Withdraw amount must be positive value");
        }
        Account account = getAccountById(withdrawDTO.getAccountId());
        boolean result = account.withdrawBalance(withdrawDTO.getCurrency(), withdrawDTO.getAmount());
        if (result) {
            logger.info("Account {} withdrew {} {}", withdrawDTO.getAccountId(), withdrawDTO.getAmount(), withdrawDTO.getCurrency());
            return new CustomResponse(modelMapper.map(saveAccount(account), AccountDTO.class));
        }
        logger.warn("Account {} failed to withdraw {} {}", withdrawDTO.getAccountId(), withdrawDTO.getAmount(), withdrawDTO.getCurrency());
        return new CustomResponse(false, "Could not withdraw");
    }

    /**
     * Exchange money from one currency to another.
     *
     * @param exchangeDTO incoming request body
     * @return response body
     */
    @Override
    public CustomResponse exchange(ExchangeDTO exchangeDTO) {
        // User should not be able to exchange zero or negative value
        if (exchangeDTO.getAmount() <= 0) {
            logger.info("Account {} attempted to exchange zero or less {} {} into {}", exchangeDTO.getAccountId(),
                    exchangeDTO.getAmount(), exchangeDTO.getFromCurrency(), exchangeDTO.getToCurrency());
            return new CustomResponse(false, "Exchange amount must be positive value");
        }
        Account account = getAccountById(exchangeDTO.getAccountId());
        double convertedValue = currencyService.convertCurrency(exchangeDTO.getFromCurrency(), exchangeDTO.getToCurrency(), exchangeDTO.getAmount());
        boolean result = account.exchange(exchangeDTO.getFromCurrency(), exchangeDTO.getToCurrency(), exchangeDTO.getAmount(), convertedValue);
        if (result) {
            logger.info("Account {} exchanged {} {} into {} {}", account.getId(), exchangeDTO.getAmount(), exchangeDTO.getFromCurrency(), convertedValue, exchangeDTO.getToCurrency());
            return new CustomResponse(modelMapper.map(saveAccount(account), AccountDTO.class));
        }
        logger.warn("Account {} failed to exchange {} {} into {}", exchangeDTO.getAccountId(), exchangeDTO.getAmount(), exchangeDTO.getFromCurrency(), exchangeDTO.getToCurrency());
        return new CustomResponse(false, "Could not exchange");
    }
}
