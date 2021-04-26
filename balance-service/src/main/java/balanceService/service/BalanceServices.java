package balanceService.service;

import balanceService.entity.Balance;
import balanceService.entity.Transaction;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class BalanceServices implements BalanceCalculator, PanacheMongoRepository<Balance> {

    @Override
    public Optional<Balance> findByAccountId(String accountId) {
        return find("accountId", accountId).firstResultOptional();
    }

    @Override
    public Balance processTransaction(Transaction transaction) {
        System.out.println("processing transaction " + transaction);
        final Balance toUpdateBalance = findByAccountId(transaction.getAccountId())
                .map(balance -> balance.recalculateBalance(transaction))
                .orElse(Balance.makeNewAccount(transaction.getAccountId()));

        System.out.println("persisting balance " + toUpdateBalance);
        persistOrUpdate(toUpdateBalance);
        System.out.println("updated " + toUpdateBalance);

        return toUpdateBalance;

    }
}
