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

    /**
     * clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night
     * @param icon
     * @return
     */
    public static WeatherIcon fromString(String icon) {
        switch (icon) {
            case "clear-day":
                return CLEAR_DAY;
                //TODO: cases with all weathertypes
            default:
                return null;
        }
    }
}
