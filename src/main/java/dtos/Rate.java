package dtos;

/**
 *
 * @author claes
 */
public class Rate {
    
    public String currency;


    public Rate(String currency) {
        this.currency = currency;

    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Rate{" + "currency=" + currency + '}';
    }

  
    

}