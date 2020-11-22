package jokeEntities;

/**
 *
 * @author claes
 */
public class Quote {
    
    private String quote;
    private String url;

    public Quote(String quote, String url) {
        this.quote = quote;
        this.url = url;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    
    
    
}
