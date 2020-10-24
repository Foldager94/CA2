/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.FacadeCityinfo;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author Nicol
 */
@Path("cityinfo")
public class CityinfoResource {

    @Context
    private UriInfo context;
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final FacadeCityinfo FACADE = FacadeCityinfo.getFacadeCityinfo(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of CityinfoResource
     */
    public CityinfoResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CityinfoResource
     *
     * @return an instance of java.lang.String
     */
//    @GET
//    @Path("all")
//    public String getAllCityinfos() {
//        List<Cityinfo> citys = FACADE.getAllCityinfo();
//        return GSON.toJson(citys);
//    }
 @Path("/all")
        @GET
        @Produces({MediaType.APPLICATION_JSON})
        public Response getAll() {
            return Response.ok().entity(GSON.toJson(FACADE.getAllCityinfo())).build();
    }
  
}
