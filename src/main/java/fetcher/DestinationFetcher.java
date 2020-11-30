package fetcher;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import com.nimbusds.jose.shaded.json.JSONObject;
import dtos.CombinedDTO;
import dtos.CovidInfoDTO;
import dtos.ExchangeRatesDTO;
import dtos.Rate;
import dtos.RatesDTO;
import java.util.Iterator;
import static javax.ws.rs.client.Entity.json;
import jdk.nashorn.internal.parser.JSONParser;
import org.eclipse.persistence.sessions.serializers.JSONSerializer;

/**
 *
 * @author claes
 */
public class DestinationFetcher {

    final static String DESTINATION_SERVER = "https://restcountries.eu/rest/v2/name/";
    final static String RATES_SERVER = "https://api.exchangeratesapi.io/latest?base=USD&symbols=";
    final static String COVID_SERVER = "https://covid19-api.org/api/status/";

    static String currencyCode;
    static String countryCode;

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static double calculateInfectionRate(double population, double cases) {
        double rate = (cases / population) * 100;
        return Math.round(rate * 100.0) / 100.0;
    }

    public static String getDestination(String country, ExecutorService threadPool, final Gson gson) throws IOException, InterruptedException, ExecutionException, TimeoutException {

        Callable<DestinationDTO> destTask = new Callable<DestinationDTO>() {
            @Override
            public DestinationDTO call() throws IOException {
                String dest = HttpUtils.fetchData(DESTINATION_SERVER + country);
                Type listCity = new TypeToken<ArrayList<DestinationDTO>>() {
                }.getType();
                ArrayList<DestinationDTO> cityArray = gson.fromJson(dest, listCity);

                currencyCode = cityArray.get(0).getCurrencies().get(0).getCode();
                countryCode = cityArray.get(0).getAlpha2Code();

                return cityArray.get(0);
            }
        };

        Future<DestinationDTO> futureDestination = threadPool.submit(destTask);

        DestinationDTO destinationDTO = futureDestination.get();

        ExchangeRatesDTO exchangeRatesDTO = getRates(currencyCode, threadPool, gson);

        CovidInfoDTO covidDTO = getCovidInfo(countryCode, threadPool, gson);

        double cases = (double) covidDTO.getCases();
        double population = (double) destinationDTO.getPopulation();

        destinationDTO.setInfectionRate(calculateInfectionRate(population, cases));

        CombinedDTO combinedDTO = new CombinedDTO(destinationDTO, exchangeRatesDTO, covidDTO);

        String combinedDTOString = gson.toJson(combinedDTO);

        return combinedDTOString;
    }

    public static ExchangeRatesDTO getRates(String code, ExecutorService threadPool, final Gson gson) throws IOException, InterruptedException, ExecutionException, TimeoutException {

        Callable<ExchangeRatesDTO> destTask = new Callable<ExchangeRatesDTO>() {
            @Override
            public ExchangeRatesDTO call() throws IOException {

                String rates = HttpUtils.fetchData(RATES_SERVER + code);

                //ObjectMapper objectMapper = new ObjectMapper();
                //JsonNode jsonNode = objectMapper.readTree(rates);
                //Double fxRate = jsonNode.get(code).asDouble();
                //ExchangeRatesDTO exDTO = new ExchangeRatesDTO(fxRate);
                ExchangeRatesDTO exchangeRatesDTO = gson.fromJson(rates, ExchangeRatesDTO.class);

                //RatesDTO ratesdto = gson.fromJson(rates, RatesDTO.class);
                return exchangeRatesDTO;
            }
        };

        Future<ExchangeRatesDTO> future = threadPool.submit(destTask);

        ExchangeRatesDTO result = future.get();

        return result;
    }

    public static CovidInfoDTO getCovidInfo(String countryAlpha, ExecutorService threadPool, final Gson gson) throws IOException, InterruptedException, ExecutionException, TimeoutException {

        Callable<CovidInfoDTO> destTask = new Callable<CovidInfoDTO>() {
            @Override
            public CovidInfoDTO call() throws IOException {

                String rates = HttpUtils.fetchData(COVID_SERVER + countryAlpha);

                CovidInfoDTO covidDTO = gson.fromJson(rates, CovidInfoDTO.class);

                return covidDTO;
            }
        };

        Future<CovidInfoDTO> future = threadPool.submit(destTask);

        CovidInfoDTO result = future.get();

        return result;
    }

    public static RatesDTO getRates2(String code) throws IOException, InterruptedException, ExecutionException, TimeoutException {

        String rates = HttpUtils.fetchData(RATES_SERVER + code);
        
         ObjectMapper objectMapper = new ObjectMapper();
         
         RatesDTO ratedto = new RatesDTO();
         
      try {
         JsonNode node = objectMapper.readValue(rates, JsonNode.class);
         JsonNode child = node.get("rates");
         JsonNode childField = child.get(code);
         Double rate = childField.asDouble();
         System.out.println(rate);
         ratedto.setRate(rate);
         System.out.println(ratedto);
         
      } catch (IOException e) {
         e.printStackTrace();
      }return ratedto;

    }

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException, ExecutionException {
        System.out.println(getRates2("HUF"));
    }
}
