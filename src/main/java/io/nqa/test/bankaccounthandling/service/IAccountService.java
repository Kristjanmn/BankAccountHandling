package io.nqa.test.bankaccounthandling.service;

import io.nqa.commons.CustomResponse;
import io.nqa.test.bankaccounthandling.entity.Account;
import io.nqa.test.bankaccounthandling.model.DepositDTO;
import io.nqa.test.bankaccounthandling.model.ExchangeDTO;
import io.nqa.test.bankaccounthandling.model.WithdrawDTO;

import java.util.List;
import java.util.UUID;

public interface IAccountService {
    List<Account> getAccounts();
    CustomResponse getAccountDtos();
    Account getAccountById(UUID id);
    CustomResponse getAccountDtoById(UUID id);
    CustomResponse createAccount();
    Account saveAccount(Account account);
    CustomResponse deposit(DepositDTO depositDTO);
    CustomResponse withdraw(WithdrawDTO withdrawDTO);
    CustomResponse exchange(ExchangeDTO exchangeDTO);
}
