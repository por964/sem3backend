/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Jonas
 */
public class Currency {
    
    private Double EUR;
    private Double GBP;

    public Double getGBP() {
        return GBP;
    }

    public void setGBP(Double GBP) {
        this.GBP = GBP;
    }

    public Currency(Double EUR, Double GBP) {
        this.EUR = EUR;
        this.GBP = GBP;
    }
    
    public Currency() {
    }

    public Double getEUR() {
        return EUR;
    }

    public void setEUR(Double EUR) {
        this.EUR = EUR;
    }
    
}
