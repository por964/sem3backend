/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.ExchangeRates;
import java.util.List;

/**
 *
 * @author Jonas
 */

public class ExhangeRatesDTO {
    
    private List<ExchangeRates> rates;
    private List<ExhangeRatesDTO> ExchangeRates;

    public ExhangeRatesDTO(List<ExchangeRates> rates) {
        this.rates = rates;
    }
    
    public ExhangeRatesDTO() {
    }
    
    public List<ExhangeRatesDTO> getExchangeRates() {
        return ExchangeRates;
    }

    public void setExchangeRates(List<ExhangeRatesDTO> ExchangeRates) {
        this.ExchangeRates = ExchangeRates;
    }
    
    public List<ExchangeRates> getRates() {
        return rates;
    }

    public void setRates(List<ExchangeRates> rates) {
        this.rates = rates;
    }
            
}
