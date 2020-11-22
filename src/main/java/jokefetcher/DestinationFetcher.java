package jokefetcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dtos.DestinationDTO;
import entities.Destination;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import utils.HttpUtils;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author claes
 */
public class DestinationFetcher {
    
    final static String DESTINATION_SERVER = "https://restcountries.eu/rest/v2/name/eesti?fields=name;alpha3Code;capital";
    final ObjectMapper objectMapper = new ObjectMapper();
    
    
    public static String getDestination (ExecutorService threadPool, final Gson gson) throws IOException, InterruptedException, ExecutionException, TimeoutException {
   
    Callable<DestinationDTO> destTask = new Callable<DestinationDTO>(){
        @Override
        public DestinationDTO call() throws IOException {
            String dest = HttpUtils.fetchData(DESTINATION_SERVER);
            Type listCity = new TypeToken<ArrayList<DestinationDTO>>(){}.getType();
            ArrayList<DestinationDTO> cityArray = gson.fromJson(dest, listCity); 


            
            return cityArray.get(0);
        }
    };   
    
    Future<DestinationDTO> futureDestination = threadPool.submit(destTask);
    
    DestinationDTO destination = futureDestination.get();
      
    
    
    String destinationJson = gson.toJson(destination);
    return destinationJson;
    }
    
}
