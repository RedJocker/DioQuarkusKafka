package org.tutorial.transaction.resource;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.tutorial.transaction.entity.Transaction;
import org.tutorial.transaction.producer.OutgoingProducer;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/transactions")
public class TransactionResource {


    private final Emitter<Transaction> emitter;

    @Inject
    public TransactionResource(@Channel(OutgoingProducer.transactionTopic) Emitter<Transaction> emitter) {
        this.emitter = emitter;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response add(@Valid Transaction transaction) {
        transaction.persist();

        emitter.send(transaction);

        return Response
            .created(URI.create(String.format("/transactions/%s", transaction.id)))
            .entity(transaction)
            .build();
    }


}
