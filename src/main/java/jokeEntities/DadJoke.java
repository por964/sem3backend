package jokeEntities;

/**
 *
 * @author claes
 */
public class DadJoke {
    
    private String id;
    private String joke;

    public DadJoke(String id, String joke) {
        this.id = id;
        this.joke = joke;
    }

    public String getId() {
        return id;
    }

    public String getJoke() {
        return joke;
    }
    
    
}
