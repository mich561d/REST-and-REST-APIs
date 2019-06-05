package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dto.EventDTO;
import facade.PetFacade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("pet")
public class PetResource {

    @Context
    private UriInfo context;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    PetFacade facade = new PetFacade(Persistence.createEntityManagerFactory("pu"));

    public PetResource() {
    }

    @GET
    @Path("/petcount")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPetCount() {
        JsonObject jo = new JsonObject();
        jo.addProperty("PetCount", facade.getAllPets().size());
        return Response.ok().entity(gson.toJson(jo)).build();
    }

    @GET
    @Path("/allpets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPets() {
        return Response.ok().entity(gson.toJson(facade.getAllPets())).build();
    }

    @GET
    @Path("/allpets/alive")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPetsAlive() {
        return Response.ok().entity(gson.toJson(facade.getAllPetsAlive())).build();
    }

    @GET
    @Path("/petsbyevent/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPetsByEvent(@PathParam("date") String content) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(content);
        } catch (ParseException e) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return Response.ok().entity(gson.toJson(facade.getAllPetsByEvent(date))).build();
    }

    @GET
    @Path("/event")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPetsByEvent(@QueryParam("id") int id) {
        return Response.ok().entity(gson.toJson(facade.getEventByID(id))).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEventForPet(String content) {
        EventDTO event = gson.fromJson(content, EventDTO.class);
        facade.createEvent(event);
        return Response.ok().entity(gson.toJson(event)).build();
    }
}
