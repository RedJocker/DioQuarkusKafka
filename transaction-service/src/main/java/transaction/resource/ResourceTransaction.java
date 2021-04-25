package transaction.resource;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import transaction.entity.Transaction;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

@Path("/")
public class ResourceTransaction {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        return Response.ok(Transaction.listAll()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response findById(@PathParam("id") String id) {
        final Optional<Transaction> found = Transaction.findByIdOptional(id);

        return found
                .map(transaction -> Response.ok(Map.of("found", transaction)).build())
                .orElseThrow( () -> new NotFoundException("id not found " +  id));
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(Transaction transaction) {

        transaction.persist();

        final URI uri = URI.create("/transactions/" + transaction.id);
        final Map<String, Object> response = Map.of(
                "created", transaction,
                "link", uri
        );

        return Response.created(uri).entity(response).build();
    }


    @Provider
    public static class NotFoundHandler implements ExceptionMapper<NotFoundException> {

        @Override
        public Response toResponse(NotFoundException exception) {
            return Response.status(404).entity(Map.of("error", exception.getMessage())).build();
        }
    }

}
