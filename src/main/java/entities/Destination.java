package entities;

/**
 *
 * @author claes
 */
public class Destination {
    
    private String name;
    private String alpha2Code;
    private String capital;
    private String [] altSpellings;
    private int population;

    public Destination() {
    }

    public Destination(String name, String alpha2Code, String capital, String[] altSpellings, int population) {
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.capital = capital;
        this.altSpellings = altSpellings;
        this.population = population;
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

    public String[] getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(String[] altSpellings) {
        this.altSpellings = altSpellings;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
    
    
    
}
