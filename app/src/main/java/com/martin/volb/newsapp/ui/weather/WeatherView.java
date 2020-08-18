package com.martin.volb.newsapp.ui.weather;

import com.martin.volb.newsapp.ui.newsFeed.Article;

import java.util.List;

public interface WeatherView {
    void showCurrentWeather(WeatherResponse weatherResponse);

    void showProgress();

    void hideProgress();

    void showError(String error);
}
