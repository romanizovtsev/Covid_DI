package com.example.coviddi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Post {
    @SerializedName("All")
    @Expose
    private All all;
    @SerializedName("French Guiana")
    @Expose
    private FrenchGuiana frenchGuiana;

    public All getAll() {
        return all;
    }

    public void setAll(All all) {
        this.all = all;
    }

    public FrenchGuiana getFrenchGuiana() {
        return frenchGuiana;
    }

    public void setFrenchGuiana(FrenchGuiana frenchGuiana) {
        this.frenchGuiana = frenchGuiana;
    }

    /*@SerializedName("All.confirmed")
    @Expose
    private int confirmed;
    @SerializedName("All.iso")
    @Expose
    private int iso;
    @SerializedName("All.recovered")
    @Expose
    private int recovered;
    @SerializedName("All.deaths")
    @Expose
    private int deaths;
    @SerializedName("All.country")
    @Expose
    private String country;

    public int getIso() {
        return iso;
    }

    public void setIso(int iso) {
        this.iso = iso;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getDeath() {
        return deaths;
    }

    public void setDeath(int deaths) {
        this.deaths = deaths;
    }

     */

    public class All {
//Date date =
        @SerializedName("confirmed")
        @Expose
        private Integer confirmed;
        @SerializedName("recovered")
        @Expose
        private Integer recovered;
        @SerializedName("deaths")
        @Expose
        private Integer deaths;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("population")
        @Expose
        private Integer population;
        @SerializedName("sq_km_area")
        @Expose
        private Integer sqKmArea;
        @SerializedName("life_expectancy")
        @Expose
        private String lifeExpectancy;
        @SerializedName("elevation_in_meters")
        @Expose
        private Integer elevationInMeters;
        @SerializedName("continent")
        @Expose
        private String continent;
        @SerializedName("abbreviation")
        @Expose
        private String abbreviation;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("iso")
        @Expose
        private Integer iso;
        @SerializedName("capital_city")
        @Expose
        private String capitalCity;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("long")
        @Expose
        private String _long;
        @SerializedName("updated")
        @Expose
        private String updated;

        public Integer getConfirmed() {
            return confirmed;
        }

        public void setConfirmed(Integer confirmed) {
            this.confirmed = confirmed;
        }

        public Integer getRecovered() {
            return recovered;
        }

        public void setRecovered(Integer recovered) {
            this.recovered = recovered;
        }

        public Integer getDeaths() {
            return deaths;
        }

        public void setDeaths(Integer deaths) {
            this.deaths = deaths;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Integer getPopulation() {
            return population;
        }

        public void setPopulation(Integer population) {
            this.population = population;
        }

        public Integer getSqKmArea() {
            return sqKmArea;
        }

        public void setSqKmArea(Integer sqKmArea) {
            this.sqKmArea = sqKmArea;
        }

        public String getLifeExpectancy() {
            return lifeExpectancy;
        }

        public void setLifeExpectancy(String lifeExpectancy) {
            this.lifeExpectancy = lifeExpectancy;
        }

        public Integer getElevationInMeters() {
            return elevationInMeters;
        }

        public void setElevationInMeters(Integer elevationInMeters) {
            this.elevationInMeters = elevationInMeters;
        }

        public String getContinent() {
            return continent;
        }

        public void setContinent(String continent) {
            this.continent = continent;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Integer getIso() {
            return iso;
        }

        public void setIso(Integer iso) {
            this.iso = iso;
        }

        public String getCapitalCity() {
            return capitalCity;
        }

        public void setCapitalCity(String capitalCity) {
            this.capitalCity = capitalCity;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLong() {
            return _long;
        }

        public void setLong(String _long) {
            this._long = _long;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

    }

   /* public class Example {

        @SerializedName("All")
        @Expose
        private All all;
        @SerializedName("French Guiana")
        @Expose
        private FrenchGuiana frenchGuiana;

        public All getAll() {
            return all;
        }

        public void setAll(All all) {
            this.all = all;
        }

        public FrenchGuiana getFrenchGuiana() {
            return frenchGuiana;
        }

        public void setFrenchGuiana(FrenchGuiana frenchGuiana) {
            this.frenchGuiana = frenchGuiana;
        }

    }*/



    public class FrenchGuiana {

        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("long")
        @Expose
        private String _long;
        @SerializedName("confirmed")
        @Expose
        private Integer confirmed;
        @SerializedName("recovered")
        @Expose
        private Integer recovered;
        @SerializedName("deaths")
        @Expose
        private Integer deaths;
        @SerializedName("updated")
        @Expose
        private String updated;

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLong() {
            return _long;
        }

        public void setLong(String _long) {
            this._long = _long;
        }

        public Integer getConfirmed() {
            return confirmed;
        }

        public void setConfirmed(Integer confirmed) {
            this.confirmed = confirmed;
        }

        public Integer getRecovered() {
            return recovered;
        }

        public void setRecovered(Integer recovered) {
            this.recovered = recovered;
        }

        public Integer getDeaths() {
            return deaths;
        }

        public void setDeaths(Integer deaths) {
            this.deaths = deaths;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

    }
}
