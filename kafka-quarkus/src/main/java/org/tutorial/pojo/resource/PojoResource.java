package org.tutorial.pojo.resource;

import org.tutorial.hello.entity.GreetingEntity;
import org.tutorial.pojo.entity.PojoEntity;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;

@Path("/pojo")
public class PojoResource {

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMessageById(@PathParam("id") Long id) {
        return PojoEntity.findByIdOptional(id)
                .map(entity -> Response.status(201).entity(entity).build())
                .orElseThrow(NotFoundException::new);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(@Valid PojoEntity pojoEntity) throws URISyntaxException {
        pojoEntity.persist();

        final Map<String, Object> responseMessage = Map.of(
                "savedPojo", pojoEntity
        );

        return Response.ok(responseMessage).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        final var response = Map.of("list of pojos", PojoEntity.listAll());
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        final Optional<PojoEntity> entity = GreetingEntity.findByIdOptional(id);
        entity.ifPresent(PojoEntity::delete);


        final Map<String, Object> response = entity.map(e -> Map.of("deleted", (Object) e))
                .orElseThrow(() -> new NotFoundException("id not found " + id));

        return Response.ok(response).build();

    }
}
