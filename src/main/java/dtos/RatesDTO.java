package dtos;

/**
 *
 * @author claes
 */
public class RatesDTO {
    
    public Double rate;

    public RatesDTO(Double rate) {
        this.rate = rate;
    }

    public RatesDTO() {
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "RatesDTO: " + rate;
    }

    
    
    

}