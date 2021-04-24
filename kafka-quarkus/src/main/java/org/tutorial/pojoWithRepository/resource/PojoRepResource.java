package org.tutorial.pojoWithRepository.resource;

import org.tutorial.hello.entity.GreetingEntity;
import org.tutorial.pojo.entity.PojoEntity;
import org.tutorial.pojoWithRepository.entity.PojoRepEntity;
import org.tutorial.pojoWithRepository.repository.PojoRepRepository;

import javax.inject.Inject;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Path("/pojorep")
public class PojoRepResource {

    @Inject
    PojoRepRepository pojoRepRepository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMessageById(@PathParam("id") Long id) {
        return pojoRepRepository.findByIdOptional(id)
                .map(entity -> Response.status(201).entity(entity).build())
                .orElseThrow(NotFoundException::new);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(@Valid PojoRepEntity pojoRepEntity) throws URISyntaxException {

        pojoRepRepository.persist(pojoRepEntity);

        final Map<String, Object> responseMessage = Map.of(
                "savedPojo", pojoRepEntity
        );

        return Response.ok(responseMessage).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        final var response = Map.of("list of pojosrep", pojoRepRepository.listAll());
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        final Optional<PojoRepEntity> entity = pojoRepRepository.findByIdOptional(id);
        entity.ifPresent(pojoRepRepository::delete);


        final Map<String, Object> response = entity.map(e -> Map.of("deleted", (Object) e))
                .orElseThrow(() -> new NotFoundException("id not found " + id));

        return Response.ok(response).build();

    }

    @GET
    @Path("/customList/{lessThan}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCustom(@PathParam("lessThan") String lessThan){
        final List<PojoRepEntity> pojoRepEntities = pojoRepRepository.customFindQuery(lessThan);

        final var response = Map.of(
                "custom list of pojosrep", pojoRepEntities
        );

       return Response.ok(response).build();
    }
}
