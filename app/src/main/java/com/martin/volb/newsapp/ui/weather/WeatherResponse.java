package com.martin.volb.newsapp.ui.weather;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("currently")
    WeatherData currentWeather;
    @SerializedName("alerts")
    WeatherAlert weatherAlert;
    @SerializedName("daily")
    DailyWeather dailyWeather;

    public WeatherResponse(WeatherData currentWeather, WeatherAlert weatherAlert, DailyWeather dailyWeather) {
        this.currentWeather = currentWeather;
        this.weatherAlert = weatherAlert;
        this.dailyWeather = dailyWeather;
    }

    public WeatherData getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(WeatherData currentWeather) {
        this.currentWeather = currentWeather;
    }

    public WeatherAlert getWeatherAlert() {
        return weatherAlert;
    }

    public void setWeatherAlert(WeatherAlert weatherAlert) {
        this.weatherAlert = weatherAlert;
    }

    public DailyWeather getDailyWeather() {
        return dailyWeather;
    }

    public void setDailyWeather(DailyWeather dailyWeather) {
        this.dailyWeather = dailyWeather;
    }
}
