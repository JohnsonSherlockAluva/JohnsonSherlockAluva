package com.goethe.covidservice.model;

import java.io.Serializable;
/**
 * Quantity entity.\n@author The Johnson George.
 */
public class Quantity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String county;



    private String stateName;
    private Long cases;
    private Long deaths;
    private Double death_rate;
    private Double cases_per_population;
    private Long ewz;
    private String updated_on;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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

    public Double getDeath_rate() {
        return death_rate;
    }

    public void setDeath_rate(Double death_rate) {
        this.death_rate = death_rate;
    }

    public Double getCases_per_population() {
        return cases_per_population;
    }

    public void setCases_per_population(Double cases_per_population) {
        this.cases_per_population = cases_per_population;
    }

    public Long getEwz() {
        return ewz;
    }

    public void setEwz(Long ewz) {
        this.ewz = ewz;
    }

    public String getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(String updated_on) {
        this.updated_on = updated_on;
    }


    @Override
    public String toString() {
        return "Quantity{" +
                "id=" + id +
                ", county='" + county + '\'' +
                ", stateName='" + stateName + '\'' +
                ", cases=" + cases +
                ", deaths=" + deaths +
                ", death_rate=" + death_rate +
                ", cases_per_population=" + cases_per_population +
                ", ewz=" + ewz +
                ", updated_on='" + updated_on + '\'' +
                '}';
    }




}
