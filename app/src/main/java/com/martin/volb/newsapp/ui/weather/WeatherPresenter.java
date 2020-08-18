package com.martin.volb.newsapp.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherPresenter {
    private WeatherView weatherView;

    public WeatherPresenter(WeatherView weatherView) {
        this.weatherView = weatherView;
    }

    public void requestData(String apiKey) {
        weatherView.showProgress();
        WeatherService service = RetroFitClientWeather.getRetrofitInstance().create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentWeather(); //TODO: getUserLocation

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                weatherView.hideProgress();
                weatherView.showCurrentWeather((WeatherResponse) (response.body() != null ? response.body().getCurrentWeather() : new ArrayList<WeatherResponse>()));
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                weatherView.hideProgress();
                weatherView.showError(t.getLocalizedMessage());

            }
        });
    }
}