/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.HobbyDTO;
import dtos.PersonDTO;
import facades.FacadeHobby;
import facades.FacadePerson;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author Nyxis
 */
@Path("hobby")
public class HobbyResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final FacadeHobby FACADE_HOBBY = FacadeHobby.getFacadeHobby(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HobbyResource
     */
    public HobbyResource() {
    }

    /**
     * Retrieves representation of an instance of rest.HobbyResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHobbyByName(@PathParam("name") String name) {
        HobbyDTO hobbydto = FACADE_HOBBY.getHobbyByName(name);
        return GSON.toJson(hobbydto);
    }
    
    
    @GET
    @Path("/all/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPersons(@PathParam("name")String name){
        
        return GSON.toJson(FACADE_HOBBY.getAllPersonsWithHobby(name));
    }
    
    /*@POST
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addHobbyToPerson(String person) {
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        PersonDTO newPerson = FACADE_PERSON.addPerson(p);
        return GSON.toJson(newPerson);
    }
*/
    /**
     * PUT method for updating or creating an instance of HobbyResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
