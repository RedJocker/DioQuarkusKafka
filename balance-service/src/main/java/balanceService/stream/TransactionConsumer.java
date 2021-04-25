package balanceService.stream;

import balanceService.entity.Transaction;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class TransactionConsumer {

    private static final Logger log = Logger.getLogger(TransactionConsumer.class.getName());



    @ConsumeEvent(blocking = true)
    public void consume(Transaction transaction) {
        log.info(transaction.toString());

    }

}
