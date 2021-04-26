package balanceService.entity;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "balances")
public class Balance {

    public ObjectId id;
    public String accountId;
    public Double value;

    public Balance recalculateBalance(Transaction transaction) {

        if (transaction.isIncome()) {
            this.value += transaction.getValue();
        } else if(transaction.isExpense()) {
            this.value -= transaction.getValue();
        }
        return this;
    }

    public static Balance makeNewAccount(String accountId) {
        var balance = new Balance();
        balance.accountId = accountId;
        balance.value = 0.0;
        return balance;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", accountId='" + accountId + '\'' +
                ", value=" + value +
                '}';
    }
}
