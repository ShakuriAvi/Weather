package com.example.weather;

public class CityWheater {
    public String getCityName() {
        return cityName;
    }

    private String cityName;

    public String getCountry() {
        return country;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getLocalTime() {
        return localTime;
    }

    public String getDescription() {
        return description;
    }

    private String country;
    private int temperature;
    private String localTime;
    private String description;

    public CityWheater(String cityName, String country, int temperatures,String localTime,String description) {
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperatures;
        this.localTime = localTime;
        this.description=description;

    }



}
