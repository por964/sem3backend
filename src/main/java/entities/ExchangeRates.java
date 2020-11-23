/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author Jonas
 */

public class ExchangeRates {
    
    private String DKK;

    public ExchangeRates(String dkk) {
        this.DKK = dkk;
    }
    
    public ExchangeRates() {
    }
    
    public String getDKK() {
        return DKK;
    }

    public void setDKK(String DKK) {
        this.DKK = DKK;
    }

}
