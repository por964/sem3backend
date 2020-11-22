package jokeEntities;

/**
 *
 * @author claes
 */
public class ChuckJoke {
    
    private String value;
    private String url;

    public ChuckJoke(String value, String url) {
        this.value = value;
        this.url = url;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
    public String getUrl() {
        return url;
    }
    
    
    
}
