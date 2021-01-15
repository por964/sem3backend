//
//package Fetcher;
//
//import com.google.gson.Gson;
//import java.io.IOException;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeoutException;
//import utils.HttpUtils;
//
///**
// *
// * @author am
// */
//
//public class ApiFetcher {
//    
//    final static String CHUCK_SERVER = "https://api.chucknorris.io/jokes/random";
//    final static String DAD_SERVER = "https://icanhazdadjoke.com";
//    final static String INSULT_SERVER = "https://evilinsult.com/generate_insult.php?lang=en&type=json";
//
//    
//public static String responseParallel (ExecutorService threadPool, final Gson gson) throws IOException, InterruptedException, ExecutionException, TimeoutException {
//   
//    Callable<ChuckDTO> chuckTask = new Callable<ChuckDTO>(){
//        @Override
//        public ChuckDTO call() throws IOException {
//            String chuck = HttpUtils.fetchData(CHUCK_SERVER);
//            ChuckDTO chuckDTO = gson.fromJson(chuck, ChuckDTO.class);
//            return chuckDTO;
//        }
//    };      
//       Callable<DadDTO> dadTask = new Callable<DadDTO>(){
//           @Override
//           public DadDTO call() throws IOException {
//               String dad = HttpUtils.fetchData(DAD_SERVER);
//               DadDTO dadDTO = gson.fromJson(dad, DadDTO.class);
//               return dadDTO;
//           }
//           
//       };
//       
//       Callable <InsultDTO> insultTask = new Callable<InsultDTO>(){
//           @Override
//           public InsultDTO call() throws IOException {
//               String insult = HttpUtils.fetchData(INSULT_SERVER);
//               InsultDTO insultDTO = gson.fromJson(insult, InsultDTO.class);
//               return insultDTO;
//           }
//       };
//               
//       Future<ChuckDTO> futureChuck = threadPool.submit(chuckTask);
//       Future<DadDTO> futureDad = threadPool.submit(dadTask);
//       Future<InsultDTO> futureInsult = threadPool.submit(insultTask);
//       
//       ChuckDTO chuck = futureChuck.get();
//       DadDTO dad = futureDad.get();
//       InsultDTO insult = futureInsult.get();
//       
//       CombinedJokesDTO combinedDTO = new CombinedJokesDTO(chuck.getValue(), CHUCK_SERVER, dad.getJoke(), DAD_SERVER, insult.getInsult(), INSULT_SERVER);
//       String combinedJSON = gson.toJson(combinedDTO);
//       
//       return combinedJSON;
//       
//       
//       
//    }
//    
//}
