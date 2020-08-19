package com.martin.volb.newsapp.ui.weather;

public enum WeatherIcon {

    CLEAR_DAY("weather-sunny.json"),
    CLEAR_NIGHT("weather-night.json"),
    RAIN("weather-partly-shower.json"),
    SNOW("weather-snow.json"),
    SLEET("weather-snow.json"),
    WIND("weather-windy.json"),
    FOG("weather-foggy.json"),
    CLOUDY("weather-partly-cloudy.json"),
    PARTLY_CLOUDY_DAY("weather-partly-cloudy.json"),
    PARTLY_CLOUDY_NIGHT("weather-cloudynight.json");

    private String icon;

    private WeatherIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public static WeatherIcon fromString(String icon) {
        switch (icon) {
            case "clear-day":
                return CLEAR_DAY;
            case "clear-night":
                return CLEAR_NIGHT;
            case "rain":
                return RAIN;
            case "snow":
                return SNOW;
            case "sleet":
                return SLEET;
            case "wind":
                return WIND;
            case "fog":
                return FOG;
            case "cloudy":
                return CLOUDY;
            case "partly-cloudy-day":
                return PARTLY_CLOUDY_DAY;
            case "partly-cloudy-night":
                return PARTLY_CLOUDY_NIGHT;
            default:
                return null;
        }
    }
}
