package com.martin.volb.newsapp.ui.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.airbnb.lottie.LottieAnimationView;
import com.martin.volb.newsapp.R;

public class WeatherFragment extends Fragment implements WeatherView {
    private LottieAnimationView currentWeatherAnimationView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_weather, container, false);
        currentWeatherAnimationView = root.findViewById(R.id.current_weather_animation_view);

        return root;
    }

    @Override
    public void showCurrentWeather(WeatherResponse weatherResponse) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String error) {

    }
}