package dtos;

/**
 *
 * @author claes
 */
public class ChuckDTO {
    
    private String value;
    private String url = "https://api.chucknorris.io/jokes/random";


    public ChuckDTO(String value) {
        this.value = value;
    }

    public ChuckDTO() {
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

    public void setUrl(String url) {
        this.url = url;
    }

    
    
    

    
    
    
    
}
