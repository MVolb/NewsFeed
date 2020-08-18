package com.martin.volb.newsapp.ui.weather;

import com.google.gson.annotations.SerializedName;

public class WeatherData {
    @SerializedName("precipProbability")
    private String precipProbability;
    @SerializedName("icon")
    private String icon;
    @SerializedName("precipType")
    private String precipType;
    @SerializedName("temperature")
    private double temperature;
    @SerializedName("pressure")
    private double pressure;
    @SerializedName("windSpeed")
    private double windSpeed;

    public WeatherData(String precipProbability, String icon, String precipType, double temperature, double pressure, double windSpeed) {
        this.precipProbability = precipProbability;
        this.icon = icon;
        this.precipType = precipType;
        this.temperature = temperature;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
    }

    public String getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(String precipProbability) {
        this.precipProbability = precipProbability;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
