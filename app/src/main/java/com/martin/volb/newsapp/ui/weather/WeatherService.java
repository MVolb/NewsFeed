package com.martin.volb.newsapp.ui.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherService {
    @GET("forecast/{key}/{lat},{lng}?units=auto")
    Call<WeatherResponse> getCurrentWeather(@Path("key") String key,
                                            @Path("lat") double lat,
                                            @Path("lng") double lng);
}
