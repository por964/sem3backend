package entities;

/**
 *
 * @author claes
 */
public class CovidInfo {
    
    private String country;
    private String lastUpdate;
    private Long cases;
    private Long deaths;
    private Long recovered;

    public CovidInfo(String country, String lastUpdate, Long cases, Long deaths, Long recovered) {
        this.country = country;
        this.lastUpdate = lastUpdate;
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public CovidInfo() {
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getCases() {
        return cases;
    }

    public void setCases(Long cases) {
        this.cases = cases;
    }

    public Long getDeaths() {
        return deaths;
    }

    public void setDeaths(Long deaths) {
        this.deaths = deaths;
    }

    public Long getRecovered() {
        return recovered;
    }

    public void setRecovered(Long recovered) {
        this.recovered = recovered;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
    
    
}
