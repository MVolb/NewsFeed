package com.martin.volb.newsapp.ui.weather;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherResponse {
    @SerializedName("weather")
    private ArrayList<Weather> weatherList;
    @SerializedName("main")
    private WeatherData weatherData;
    @SerializedName("wind")
    private Wind wind;
    @SerializedName("clouds")
    private Clouds clouds;
    @SerializedName("name")
    private String city;

    public WeatherResponse(ArrayList<Weather> weatherList, WeatherData weatherData, Wind wind, Clouds clouds, String city) {
        this.weatherList = weatherList;
        this.weatherData = weatherData;
        this.wind = wind;
        this.clouds = clouds;
        this.city = city;
    }

    public ArrayList<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(ArrayList<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
