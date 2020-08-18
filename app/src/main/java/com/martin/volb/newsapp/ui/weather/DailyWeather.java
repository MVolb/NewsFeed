package com.martin.volb.newsapp.ui.weather;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DailyWeather {
    @SerializedName("data")
    private ArrayList<WeatherData> weatherData;
    @SerializedName("summary")
    private String summary;

    public DailyWeather(ArrayList<WeatherData> weatherData, String summary) {
        this.weatherData = weatherData;
        this.summary = summary;
    }

    public ArrayList<WeatherData> getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(ArrayList<WeatherData> weatherData) {
        this.weatherData = weatherData;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
