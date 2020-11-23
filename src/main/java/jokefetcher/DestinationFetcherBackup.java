package jokefetcher;

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
import dtos.ExhangeRatesDTO;

/**
 *
 * @author claes
 */

public class DestinationFetcherBackup {

    final static String DESTINATION_SERVER = "https://restcountries.eu/rest/v2/name/";
    final static String RATES_SERVER = "https://api.exchangeratesapi.io/latest?base=USD&symbols=EUR";

    public static String getDestination(String country, ExecutorService threadPool, final Gson gson) throws IOException, InterruptedException, ExecutionException, TimeoutException {

        Callable<DestinationDTO> destTask = new Callable<DestinationDTO>() {
            @Override
            public DestinationDTO call() throws IOException {
                String dest = HttpUtils.fetchData(DESTINATION_SERVER + country);
                Type listCity = new TypeToken<ArrayList<DestinationDTO>>() {
                }.getType();
                ArrayList<DestinationDTO> cityArray = gson.fromJson(dest, listCity);

                return cityArray.get(0);
            }
        };

        Future<DestinationDTO> futureDestination = threadPool.submit(destTask);

        DestinationDTO destination = futureDestination.get();

        String destinationJson = gson.toJson(destination);
        return destinationJson;
    }

    public static String getRates(ExecutorService threadPool, final Gson gson) throws IOException, InterruptedException, ExecutionException, TimeoutException {

        Callable<ExhangeRatesDTO> destTask = new Callable<ExhangeRatesDTO>() {
            @Override
            public ExhangeRatesDTO call() throws IOException {

                String rates = HttpUtils.fetchData(RATES_SERVER);

                ExhangeRatesDTO exchangeRatesDTO = gson.fromJson(rates, ExhangeRatesDTO.class);

                return exchangeRatesDTO;
            }
        };

        Future<ExhangeRatesDTO> future = threadPool.submit(destTask);

        ExhangeRatesDTO result = future.get();

        String resultJson = gson.toJson(result);
        return resultJson;
    }
}
