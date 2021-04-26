package balanceService.resource;

import balanceService.entity.Balance;
import balanceService.service.BalanceServices;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Map;
import java.util.Optional;

@Path("/balance-service")
public class ResourceBalance {

    private final BalanceServices services;

    @Inject
    public ResourceBalance(BalanceServices services) {
        this.services = services;
    }

    @GET
    @Path("/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response findByAccountId(@PathParam("accountId") String accountId) {
        final Optional<Balance> found = services.findByAccountId(accountId);
        found.ifPresentOrElse(System.out::println, () -> System.out.println("balance not found"));

        return found.map(balance -> Response.ok(balance).build())
            .orElseThrow(() -> new NotFoundException("accountId not found with id " + accountId));
    }

    @Provider
    public static class NotFoundHandler implements ExceptionMapper<NotFoundException> {

        @Override
        public Response toResponse(NotFoundException exception) {
            return Response.status(404).entity(Map.of("error", exception.getMessage())).build();
        }
    }
}
