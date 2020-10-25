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
import entities.Person;
import facades.FacadeHobby;
import facades.FacadePerson;
import java.util.List;
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
 * @author ckfol
 */
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final FacadePerson FACADE_PERSON = FacadePerson.getFacadePerson(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonId(@PathParam("id") int id){
        PersonDTO p = FACADE_PERSON.getPersonByID(id);
        return GSON.toJson(p);
    }
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPerson(String person) {
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        PersonDTO newPerson = FACADE_PERSON.addPerson(p);
        return GSON.toJson(newPerson);
    }

    @GET
    @Path("phone/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonByPhone(@PathParam("number") int number) {
        PersonDTO personDTO = FACADE_PERSON.getPersonByPhone(number);
        return GSON.toJson(personDTO);
    }
    
    

    @GET
    @Path("all")
    public String getAllPerson() {
        List<Person> hej = FACADE_PERSON.getAllPersons();
        return GSON.toJson(hej);
    }

    
    /**
     * Retrieves representation of an instance of rest.PersonResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return GSON.toJson("hej");
    }

    /**
     * PUT method for updating or creating an instance of PersonResource
     *
     * @param content representation for the resource
     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void putJson(String content) {
//    }
    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String updatePerson(@PathParam("id") int id, String person) {
        PersonDTO personDTO = GSON.fromJson(person, PersonDTO.class);
        personDTO.setId(id);
        PersonDTO updatePerson = FACADE_PERSON.editPerson(personDTO);
        return GSON.toJson(updatePerson);

//    @POST
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
//    public String addPerson(String person) {
//        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
//        PersonDTO newPerson = FACADE.addPerson(p);
//        return GSON.toJson(newPerson);
    }
}
