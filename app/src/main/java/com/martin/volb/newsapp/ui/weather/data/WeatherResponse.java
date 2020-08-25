package com.martin.volb.newsapp.ui.weather.data;

import com.google.gson.annotations.SerializedName;
import com.martin.volb.newsapp.ui.weather.data.DailyWeather;
import com.martin.volb.newsapp.ui.weather.data.WeatherAlert;
import com.martin.volb.newsapp.ui.weather.data.WeatherData;

import java.util.ArrayList;

public class WeatherResponse {
    @SerializedName("currently")
    private WeatherData currentWeather;
    @SerializedName("alerts")
    private ArrayList<WeatherAlert> weatherAlerts;
    @SerializedName("daily")
    private DailyWeather dailyWeather;

    public WeatherResponse(WeatherData currentWeather, ArrayList<WeatherAlert> weatherAlerts, DailyWeather dailyWeather) {
        this.currentWeather = currentWeather;
        this.weatherAlerts = weatherAlerts;
        this.dailyWeather = dailyWeather;
    }

    public WeatherData getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(WeatherData currentWeather) {
        this.currentWeather = currentWeather;
    }

    public ArrayList<WeatherAlert> getWeatherAlerts() {
        return weatherAlerts;
    }

    public void setWeatherAlerts(ArrayList<WeatherAlert> weatherAlerts) {
        this.weatherAlerts = weatherAlerts;
    }

    public DailyWeather getDailyWeather() {
        return dailyWeather;
    }

    public void setDailyWeather(DailyWeather dailyWeather) {
        this.dailyWeather = dailyWeather;
    }
}
