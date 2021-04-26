package balanceService.stream;

import balanceService.entity.Transaction;
import balanceService.service.BalanceServices;
import io.vertx.kafka.client.serialization.JsonObjectDeserializer;
import io.vertx.kafka.client.serialization.JsonObjectSerializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class IncomeTransactionEvent {

    private static final Logger log = Logger.getLogger(IncomeTransactionEvent.class.getName());
    private static final String transaction = "transactions";

    private final BalanceServices service;

    @Inject
    public IncomeTransactionEvent(BalanceServices service) {
        this.service = service;
    }


    @Produces
    public Topology onTransactionTopology() {
        var builder = new StreamsBuilder();
        builder.stream(
                transaction,
                Consumed.with(Serdes.String(),
                        Serdes.serdeFrom(
                                new JsonObjectSerializer(), new JsonObjectDeserializer()))
        ).foreach((key, value) -> {
                log.info("Receiving transaction " + value);
                var transaction = Transaction.ofMap(value.getMap());
                log.info("Receiving transaction with description " + transaction.getDescription());
                System.out.println(transaction);
                service.processTransaction(transaction);
        });
        return builder.build();
    }


}
