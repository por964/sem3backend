package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ExchangeRatesDTO;
import entities.ExchangeRates;
import errorhandling.MissingInputException;
import facades.UserFacade;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import fetcher.DestinationFetcher;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.POST;
import utils.EMF_Creator;
import utils.HttpUtils;

@Path("destination")
public class DestinationResource {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final ExecutorService es = Executors.newCachedThreadPool();
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final UserFacade FACADE = UserFacade.getUserFacade(EMF);

    @GET
    @Path("open/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOpenDestination(@PathParam("country") String country) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        String result = DestinationFetcher.getDestination(country, es, gson);
        return result;
    }
    
    @GET
    @Path("restricted/{country}")
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRestrictedDestination(@PathParam("country") String country) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        String result = DestinationFetcher.getDestination(country, es, gson);
        return result;
    }
    
    @POST
    @Path("open/{country}/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String saveFavourite(@PathParam("country") String country, @PathParam("userName") String userName) throws IOException, InterruptedException, ExecutionException, TimeoutException, MissingInputException {
        String result = FACADE.addFavourite(country, userName);
        return result;
    }
}
