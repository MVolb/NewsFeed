package com.martin.volb.newsapp.ui.weather;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.martin.volb.newsapp.R;

public class ForecastView extends LinearLayout {
    private LottieAnimationView forecastWeatherAnimationView;
    private TextView forecastTemperatureView;
    private TextView forecastDescriptionView;

    public ForecastView(Context context) {
        this(context, null);
    }

    public ForecastView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ForecastView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_forecast, this, true);
        forecastDescriptionView = findViewById(R.id.forecast_weather_day_tv);
        forecastTemperatureView = findViewById(R.id.forecast_weather_temperature_tv);
        forecastWeatherAnimationView = findViewById(R.id.forecast_weather_animation_view);
    }

    public void setWeather(WeatherData data) {
        forecastWeatherAnimationView.setAnimation(WeatherIcon.fromString(data.getIcon()).getIcon());
        forecastTemperatureView.setText(data.getTemperatureMin() + " - " + data.getTemperatureMax() + "°C");
        forecastDescriptionView.setText(data.getSummary());
    }
}
