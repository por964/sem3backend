package jokeEntities;

/**
 *
 * @author claes
 */
public class Insult {
    
    private String insult;
    private String url;

    public Insult(String insult, String url) {
        this.insult = insult;
        this.url = url;
    }

    public String getInsult() {
        return insult;
    }

    public void setInsult(String insult) {
        this.insult = insult;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    
}
