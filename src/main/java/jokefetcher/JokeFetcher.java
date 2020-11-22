package jokefetcher;

import com.google.gson.Gson;
import dtos.ChuckDTO;
import dtos.CombinedJokesDTO;
import dtos.DadDTO;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import utils.HttpUtils;



public class JokeFetcher {
    
    final static String CHUCK_SERVER = "https://api.chucknorris.io/jokes/random";
    final static String DAD_SERVER = "https://icanhazdadjoke.com";

    
public static String responseParallel (ExecutorService threadPool, final Gson gson) throws IOException, InterruptedException, ExecutionException, TimeoutException {
   
    Callable<ChuckDTO> chuckTask = new Callable<ChuckDTO>(){
        @Override
        public ChuckDTO call() throws IOException {
            String chuck = HttpUtils.fetchData(CHUCK_SERVER);
            ChuckDTO chuckDTO = gson.fromJson(chuck, ChuckDTO.class);
            return chuckDTO;
        }
    };      
       Callable<DadDTO> dadTask = new Callable<DadDTO>(){
           @Override
           public DadDTO call() throws IOException {
               String dad = HttpUtils.fetchData(DAD_SERVER);
               DadDTO dadDTO = gson.fromJson(dad, DadDTO.class);
               return dadDTO;
           }
           
       };
       Future<ChuckDTO> futureChuck = threadPool.submit(chuckTask);
       Future<DadDTO> futureDad = threadPool.submit(dadTask);
       
       ChuckDTO chuck = futureChuck.get();
       DadDTO dad = futureDad.get();
       CombinedJokesDTO combinedDTO = new CombinedJokesDTO(chuck.getValue(), CHUCK_SERVER, dad.getJoke(), DAD_SERVER);
       String combinedJSON = gson.toJson(combinedDTO);
       
       return combinedJSON;
       
       
       
    }

}    

