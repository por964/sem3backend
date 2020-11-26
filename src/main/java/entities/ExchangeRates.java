package entities;

import java.util.List;

/**
 *
 * @author Jonas
 */

public class ExchangeRates {
    
    private Currency currency;

    public ExchangeRates() {
    }

    public ExchangeRates(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

}
