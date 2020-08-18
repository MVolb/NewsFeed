package com.martin.volb.newsapp.ui.weather;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DailyWeather {
    @SerializedName("data")
    private ArrayList<WeatherData> weatherData;
    @SerializedName("summary")
    private String summary;
}
