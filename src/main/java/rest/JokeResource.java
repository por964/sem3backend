package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ChuckDTO;
import dtos.CombinedJokesDTO;
import dtos.DadDTO;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import jokefetcher.JokeFetcher;
import utils.HttpUtils;

@Path("jokes")
public class JokeResource {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final ExecutorService es = Executors.newCachedThreadPool();

    @Context
    private UriInfo context;
    
    //Gson gson = new Gson();

   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJokes() throws IOException {
        
        String chuck = HttpUtils.fetchData("https://api.chucknorris.io/jokes/random");
        ChuckDTO chuckDTO = gson.fromJson(chuck, ChuckDTO.class);
        
        String dad = HttpUtils.fetchData("https://icanhazdadjoke.com");
        DadDTO dadDTO = gson.fromJson(dad, DadDTO.class);
        
        CombinedJokesDTO comb = new CombinedJokesDTO(dadDTO, chuckDTO);
        
        String combinedJSON = gson.toJson(comb);
        return combinedJSON;
        
       }
    
    @GET
    @Path("parallel")
    @Produces (MediaType.APPLICATION_JSON)
    public String getJokesParallel() throws IOException, InterruptedException, ExecutionException, TimeoutException {
       String result = JokeFetcher.responseParallel(es, gson);
       return result;
    }
    

   
}
