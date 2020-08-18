package com.martin.volb.newsapp.ui.weather;

import com.martin.volb.newsapp.ui.newsFeed.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("weather")
    Call<WeatherResponse> getCurrentWeather(@Query("q") String location, @Query("appid") String apiKey);
}
