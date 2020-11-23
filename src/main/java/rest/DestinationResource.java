package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ExhangeRatesDTO;
import entities.ExchangeRates;
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
import jokefetcher.DestinationFetcher;
import utils.HttpUtils;

/**
 *
 * @author claes
 */
@Path("dest")
public class DestinationResource {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final ExecutorService es = Executors.newCachedThreadPool();

    @GET
    @Path("/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDestination(@PathParam("country") String country) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        String result = DestinationFetcher.getDestination(country, es, gson);
        return result;
    }
    
    @GET
    @Path("/rates")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRates() throws IOException, InterruptedException, ExecutionException, TimeoutException {
        //String rates = DestinationFetcher.getRates(es, gson);
        //return rates;
        
        String URL = "https://api.exchangeratesapi.io/latest?base=USD";
        
        String rates = HttpUtils.fetchData(URL);
        ExhangeRatesDTO exchangeRatesDTO = gson.fromJson(rates, ExhangeRatesDTO.class);
       
       String exchangeRates = gson.toJson(exchangeRatesDTO);
       
       return exchangeRates;
    }
}
