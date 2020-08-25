package com.martin.volb.newsapp.ui.weather;

import com.martin.volb.newsapp.ui.weather.data.WeatherResponse;
import com.martin.volb.newsapp.ui.weather.network.RetroFitClientWeather;
import com.martin.volb.newsapp.ui.weather.network.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherPresenter {
    private WeatherView weatherView;

    public WeatherPresenter(WeatherView weatherView) {
        this.weatherView = weatherView;
    }

    public void requestData(String apiKey, double lat, double lng) {
        WeatherService service = RetroFitClientWeather.getRetrofitInstance().create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentWeather(apiKey, lat, lng);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                weatherView.showCurrentWeather(response.body());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                weatherView.showError(t.getLocalizedMessage());
            }
        });
    }
}