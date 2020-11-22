package dtos;

import java.util.List;

/**
 *
 * @author claes
 */
public class DestinationDTO {
    
    private String name;
    private String alpha3Code;
    private String capital;
    private List<DestinationDTO> destinations;

    public DestinationDTO() {
    }

    public DestinationDTO(String name, String alpha3Code, String capital) {
        this.name = name;
        this.alpha3Code = alpha3Code;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<DestinationDTO> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DestinationDTO> destinations) {
        this.destinations = destinations;
    }
    
    
    
    



    
    
    
}
