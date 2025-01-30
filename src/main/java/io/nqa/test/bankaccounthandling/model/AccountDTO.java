package io.nqa.test.bankaccounthandling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * Object used to send account information to client.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private UUID id;
    private List<BalanceDTO> balances;
}
