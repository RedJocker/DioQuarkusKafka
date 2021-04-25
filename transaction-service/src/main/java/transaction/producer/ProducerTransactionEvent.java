package transaction.producer;

import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import transaction.entity.Transaction;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class ProducerTransactionEvent {

    public static final String transactionEvent = "transaction-event";

    private static final String transactions = "transactions";
    private static final Logger log = Logger.getLogger(ProducerTransactionEvent.class.getName());



    @Incoming(transactionEvent)
    @Outgoing(transactions)
    public KafkaRecord<String, Transaction> produce(Transaction transaction) {
        log.info("Sending transaction with description " + transaction.description);
        return KafkaRecord.of(UUID.randomUUID().toString(), transaction);
    }
}
