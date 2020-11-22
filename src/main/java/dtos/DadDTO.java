package dtos;

/**
 *
 * @author claes
 */
public class DadDTO {
    
    private String url = "https://icanhazdadjoke.com";
    private String joke;


    public DadDTO(String joke) {
        this.url = url;

    }

    public DadDTO() {
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    
    
    
    
    
    
}
