package com.martin.volb.newsapp.ui.weather;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.martin.volb.newsapp.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class WeatherFragment extends Fragment implements WeatherView, LocationListener {
    private LottieAnimationView currentWeatherAnimationView;
    private WeatherPresenter presenter;
    private LocationManager locationManager;
    private static final int LOCATION_REQUEST_CODE = 100;

    private TextView currentTemperatureView;
    private TextView currentWindView;
    private TextView currentPrecipitationTypeView;
    private TextView currentPrecipitationProbabilityView;
    private TextView currentSummaryView;
    private TextView current_weather_location_tv;
    private ForecastView forecastViewFirst;
    private ForecastView forecastViewSecond;
    private ForecastView forecastViewThird;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_weather, container, false);
        currentWeatherAnimationView = root.findViewById(R.id.current_weather_animation_view_current);
        presenter = new WeatherPresenter(this);
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        if (isLocationPermissionEnabled()) {
            requestLocation();
        } else if (canAskForLocationPermission()) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {
            Toast.makeText(getContext(), R.string.no_location_permission, Toast.LENGTH_LONG).show();
        }

        currentTemperatureView = root.findViewById(R.id.current_weather_temperature_tv);
        currentWindView = root.findViewById(R.id.current_weather_wind_tv);
        currentPrecipitationTypeView = root.findViewById(R.id.current_weather_precipitation_type_tv);
        currentPrecipitationProbabilityView = root.findViewById(R.id.current_weather_precipitation_probability_tv);
        currentSummaryView = root.findViewById(R.id.current_weather_summary_tv);
        current_weather_location_tv = root.findViewById(R.id.current_weather_location);
        forecastViewFirst = root.findViewById(R.id.forecast_view_first);
        forecastViewSecond = root.findViewById(R.id.forecast_view_second);
        forecastViewThird = root.findViewById(R.id.forecast_view_third);

        return root;
    }

    private boolean isLocationPermissionEnabled() {
        return ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean canAskForLocationPermission() {
        return !ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @SuppressLint("MissingPermission")
    private void requestLocation() {
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, getActivity().getMainLooper());
    }

    @Override
    public void showCurrentWeather(WeatherResponse weatherResponse) {
        WeatherIcon currentWeatherIcon = WeatherIcon.fromString(weatherResponse.currentWeather.getIcon());
        currentWeatherAnimationView.setAnimation(currentWeatherIcon.getIcon());
        currentWeatherAnimationView.playAnimation();
        currentTemperatureView.setText("Temp: " + weatherResponse.currentWeather.getTemperature() + " °C");
        currentWindView.setText("Wind: " + weatherResponse.currentWeather.getWindSpeed() + " m/s");
        if (weatherResponse.currentWeather.getPrecipType() != null) {
            currentPrecipitationTypeView.setText("Type: " + weatherResponse.currentWeather.getPrecipType().toUpperCase());
        } else {
            currentPrecipitationTypeView.setText("Type: -");
        }
        double currentPrecipitationProbability = Double.valueOf(weatherResponse.getCurrentWeather().getPrecipProbability()) * 100;
        currentPrecipitationProbabilityView.setText("Prob: " + (int)currentPrecipitationProbability + "%");
        currentSummaryView.setText(weatherResponse.currentWeather.getSummary());


        forecastViewFirst.setWeather(weatherResponse.getDailyWeather().getWeatherData().get(0));
        forecastViewSecond.setWeather(weatherResponse.getDailyWeather().getWeatherData().get(1));
        forecastViewThird.setWeather(weatherResponse.getDailyWeather().getWeatherData().get(2));
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

    @Override
    public void onDetach() {
        locationManager.removeUpdates(this);
        super.onDetach();
    }

    @Override
    public void onLocationChanged(Location location) {
        Float[] savedLocation = getSavedLocation();
        Double lastLocationLatitude = savedLocation[0].doubleValue();
        Double lastLocationLongitude = savedLocation[1].doubleValue();

        if (lastLocationLatitude != 0.0 && lastLocationLongitude != 0.0 ) {
            presenter.requestData(getString(R.string.weather_api_key), lastLocationLatitude, lastLocationLongitude);
            current_weather_location_tv.setText(getLocationName(location));
        } else {
            presenter.requestData(getString(R.string.weather_api_key), location.getLatitude(), location.getLongitude());
        }

        presenter.requestData(getString(R.string.weather_api_key), location.getLatitude(), location.getLongitude());
        lastLocationLatitude = location.getLatitude();
        lastLocationLongitude = location.getLongitude();
        Float lastLocationLatitudeSave = lastLocationLatitude.floatValue();
        Float lastLocationLongitudeSave = lastLocationLongitude.floatValue();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat("lastLocationLatitudeSave", lastLocationLatitudeSave);
        editor.putFloat("lastLocationLongitudeSave", lastLocationLongitudeSave);
        editor.apply();
        current_weather_location_tv.setText(getLocationName(location));
    }

    private Float[] getSavedLocation() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Float[] savedLocation = {preferences.getFloat("lastLocationLatitudeSave", 0.0f), preferences.getFloat("lastLocationLongitudeSave", 0.0f)};
        return savedLocation;
    }

    private String getLocationName(Location location) {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            Address address = addresses.get(0);
            return address.getLocality() + ", " + address.getCountryName();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocation();
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        provider.getBytes();
    }

    @Override
    public void onProviderEnabled(String provider) {
        provider.getBytes();
    }

    @Override
    public void onProviderDisabled(String provider) {
        provider.getBytes();
    }
}