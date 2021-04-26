package balanceService.service;

import balanceService.entity.Balance;
import balanceService.entity.Transaction;


import java.util.Optional;

public interface BalanceCalculator {


    public Optional<Balance> findByAccountId(String accountId);


    Balance processTransaction(Transaction transaction);
}
