package org.tutorial.transaction.producer;

import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;
import org.tutorial.transaction.entity.Transaction;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class OutgoingProducer {

    final public static String transactionTopic = "transactions-event";
    final private static String emittedTopic = "transactions";
    final Logger log = Logger.getLogger("OutgoingProducer");

    @Incoming(transactionTopic)
    @Outgoing(emittedTopic)
    public KafkaRecord<String, Transaction> produceTopic(Transaction transaction) {
        log.info("Sending transaction with description " + transaction.description);
        return KafkaRecord.of(UUID.randomUUID().toString(), transaction);
    }

//    @Outgoing(transactionTopic)
//    public PublisherBuilder<Transaction> produceTransaction() {
//        return
//
//    }
}
