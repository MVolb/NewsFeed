package com.martin.volb.newsapp.ui.weather;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.martin.volb.newsapp.R;

public class WeatherFragment extends Fragment implements WeatherView, LocationListener {
    private LottieAnimationView currentWeatherAnimationView;
    private WeatherPresenter presenter;
    private LocationManager locationManager;
    private static final int LOCATION_REQUEST_CODE = 100;

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
            Toast.makeText(getContext(),R.string.no_location_permission, Toast.LENGTH_LONG).show();
        }

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
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, getActivity().getMainLooper());
    }

    @Override
    public void showCurrentWeather(WeatherResponse weatherResponse) {
        WeatherIcon icon = WeatherIcon.fromString(weatherResponse.currentWeather.getIcon());
        currentWeatherAnimationView.setAnimation(icon.getIcon());
        currentWeatherAnimationView.playAnimation();
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
    public void onLocationChanged(Location location) {
        presenter.requestData(getString(R.string.weather_api_key), location.getLatitude(), location.getLongitude());
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
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
}