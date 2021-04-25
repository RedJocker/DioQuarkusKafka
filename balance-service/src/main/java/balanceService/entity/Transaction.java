package balanceService.entity;


import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.Map;
import java.util.Optional;

@RegisterForReflection
public class Transaction {

    private final String accountId;
    private final String description;
    private final Transaction.Type transactionType;
    private final double value;

    public Transaction(String accountId, String description, Transaction.Type transactionType, double value) {
        this.accountId = accountId;
        this.description = description;
        this.transactionType = transactionType;
        this.value = value;
    }

    public boolean isIncome() {
        return transactionType.isIncome();
    }

    public String getAccountId() {
        return accountId;
    }

    public double getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static Transaction ofMap(Map<String, Object> map) {
        return new Transaction(
                Optional.ofNullable(map.get("accountId")).map(Object::toString).orElse("defaultAccountId"),
                map.get("description").toString(),
                Transaction.Type.valueOf(map.get("type").toString()),
                Double.parseDouble(map.get("value").toString()));
    }

    enum Type {
        INCOME, EXPENSE;

        public boolean isIncome(){
            return this == INCOME;
        }

        public boolean isExpense(){
            return this == EXPENSE;
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountId='" + accountId + '\'' +
                ", description='" + description + '\'' +
                ", transactionType=" + transactionType +
                ", value=" + value +
                '}';
    }
}
