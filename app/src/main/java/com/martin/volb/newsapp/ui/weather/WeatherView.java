package com.martin.volb.newsapp.ui.weather;

public interface WeatherView {
    void showCurrentWeather(WeatherResponse weatherResponse);

    void showError(String error);
}
