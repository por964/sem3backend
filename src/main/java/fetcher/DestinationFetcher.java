package fetcher;

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
import dtos.CombinedDTO;
import dtos.ExchangeRatesDTO;

/**
 *
 * @author claes
 */

public class DestinationFetcher {

    final static String DESTINATION_SERVER = "https://restcountries.eu/rest/v2/name/";
    final static String RATES_SERVER = "https://api.exchangeratesapi.io/latest?base=USD&symbols=";
    
    static String currencyCode;

    public static String getDestination(String country, ExecutorService threadPool, final Gson gson) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        
        Callable<DestinationDTO> destTask = new Callable<DestinationDTO>() {
            @Override
            public DestinationDTO call() throws IOException {
                String dest = HttpUtils.fetchData(DESTINATION_SERVER + country);
                Type listCity = new TypeToken<ArrayList<DestinationDTO>>() {
                }.getType();
                ArrayList<DestinationDTO> cityArray = gson.fromJson(dest, listCity);
                
                currencyCode = cityArray.get(0).getCurrencies().get(0).getCode();
                
                return cityArray.get(0);
            }
        };

        Future<DestinationDTO> futureDestination = threadPool.submit(destTask);

        DestinationDTO destinationDTO = futureDestination.get();

        ExchangeRatesDTO exchangeRatesDTO = getRates(currencyCode, threadPool, gson);
        
        CombinedDTO combinedDTO = new CombinedDTO(destinationDTO, exchangeRatesDTO); 
        
        String combinedDTOString = gson.toJson(combinedDTO);
        
        return combinedDTOString;
    }

    public static ExchangeRatesDTO getRates(String code, ExecutorService threadPool, final Gson gson) throws IOException, InterruptedException, ExecutionException, TimeoutException {

        Callable<ExchangeRatesDTO> destTask = new Callable<ExchangeRatesDTO>() {
            @Override
            public ExchangeRatesDTO call() throws IOException {

                String rates = HttpUtils.fetchData(RATES_SERVER+code);

                ExchangeRatesDTO exchangeRatesDTO = gson.fromJson(rates, ExchangeRatesDTO.class);

                return exchangeRatesDTO;
            }
        };

        Future<ExchangeRatesDTO> future = threadPool.submit(destTask);

        ExchangeRatesDTO result = future.get();

        return result;
    }
}
