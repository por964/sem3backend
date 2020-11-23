/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Currencies;
import entities.Currency;
import java.util.List;

/**
 *
 * @author Jonas
 */
public class CombinedDTO {
    
    private String name;
    private String alpha2Code;
    private String capital;
    private List<DestinationDTO> destinations;
    private List<Currencies> currencies;
    private Currency rates;

    public CombinedDTO(DestinationDTO destinationDTO, ExchangeRatesDTO exchangeRatesDTO) {
        this.name = destinationDTO.getName();
        this.alpha2Code = destinationDTO.getAlpha2Code();
        this.capital = destinationDTO.getCapital();
        this.destinations = destinationDTO.getDestinations();
        this.currencies = destinationDTO.getCurrencies();
        this.rates = exchangeRatesDTO.getRates();
    }

    public CombinedDTO() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
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

    public List<Currencies> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currencies> currencies) {
        this.currencies = currencies;
    }

    public Currency getRates() {
        return rates;
    }

    public void setRates(Currency rates) {
        this.rates = rates;
    }


}
