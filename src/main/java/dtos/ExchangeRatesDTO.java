package dtos;

import entities.Currency;
import entities.ExchangeRates;

/**
 *
 * @author Jonas
 */

public class ExchangeRatesDTO {
    private Double fxRate;
    private Currency rates;

    public ExchangeRatesDTO(Currency rates) {
        this.rates = rates;
    }

    public ExchangeRatesDTO(Double fxRate) {
        this.fxRate = fxRate;
    }
    
      
    public ExchangeRatesDTO() {
    }
    
    public Currency getRates() {
        return rates;
    }

    public void setRates(Currency rates) {
        this.rates = rates;
    }

    public Double getFxRate() {
        return fxRate;
    }

    public void setFxRate(Double fxRate) {
        this.fxRate = fxRate;
    }
    
    
            
}
