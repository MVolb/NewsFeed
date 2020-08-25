package com.martin.volb.newsapp.ui.weather;

import com.martin.volb.newsapp.ui.weather.data.WeatherResponse;

public interface WeatherView {
    void showCurrentWeather(WeatherResponse weatherResponse);

    void showError(String error);
}
