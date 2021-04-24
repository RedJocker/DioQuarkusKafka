package org.tutorial.resource;

import org.tutorial.entity.GreetingEntity;


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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;



@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello";
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessageById(@PathParam("id") Long id) {
        final Optional<GreetingEntity> found = GreetingEntity.findByIdOptional(id);

        return found.map( entity -> entity.message).orElse("Hello default");

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(@Valid GreetingEntity greetingEntity) throws URISyntaxException {
        greetingEntity.persist();

        final Map<String, Object> responseMessage = Map.of(
                "savedMessage", greetingEntity
        );

        return Response.ok(responseMessage).build();
    }

    @GET
    @Path("/allMessages")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        final var response = Map.of("list of messages", GreetingEntity.listAll());
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        final Optional<GreetingEntity> entity = GreetingEntity.findByIdOptional(id);
        entity.ifPresent(GreetingEntity::delete);


        final Map<String, Object> response = entity.map(e -> Map.of("deleted", (Object) e))
                .orElseThrow(() -> new NotFoundException("id not found " + id));

        return Response.ok(response).build();

    }




}