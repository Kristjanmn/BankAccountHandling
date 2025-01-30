package io.nqa.test.bankaccounthandling.controller;

import io.nqa.commons.CustomResponse;
import io.nqa.test.bankaccounthandling.model.DepositDTO;
import io.nqa.test.bankaccounthandling.model.ExchangeDTO;
import io.nqa.test.bankaccounthandling.model.WithdrawDTO;
import io.nqa.test.bankaccounthandling.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final IAccountService accountService;

    @GetMapping()
    public CustomResponse getAccounts() {
        return accountService.getAccountDtos();
    }

    @GetMapping("new")
    public CustomResponse newAccount() {
        return accountService.createAccount();
    }

    @GetMapping("{uuid}")
    public CustomResponse getAccount(@PathVariable UUID uuid) {
        return accountService.getAccountDtoById(uuid);
    }

    @PostMapping("deposit")
    public CustomResponse deposit(@RequestBody DepositDTO depositDTO) {
        return accountService.deposit(depositDTO);
    }

    @PostMapping("withdraw")
    public CustomResponse withdraw(@RequestBody WithdrawDTO withdrawDTO) {
        return accountService.withdraw(withdrawDTO);
    }

    @PostMapping("exchange")
    public CustomResponse exchange(@RequestBody ExchangeDTO exchangeDTO) {
        return accountService.exchange(exchangeDTO);
    }
}
