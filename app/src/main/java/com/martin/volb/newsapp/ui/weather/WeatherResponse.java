package com.martin.volb.newsapp.ui.weather;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("currently")
    WeatherData currentWeather;
    @SerializedName("alerts")
    WeatherAlert weatherAlert;
    @SerializedName("daily")
    DailyWeather dailyWeather;
}
