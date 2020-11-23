/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Currency;
import entities.ExchangeRates;

/**
 *
 * @author Jonas
 */

public class ExhangeRatesDTO {
    
    private Currency rates;

    public ExhangeRatesDTO(Currency rates) {
        this.rates = rates;
    }
    
    public ExhangeRatesDTO() {
    }
    
    public Currency getRates() {
        return rates;
    }

    public void setRates(Currency rates) {
        this.rates = rates;
    }
            
}
