package com.martin.volb.newsapp.ui.weather.data;

import com.google.gson.annotations.SerializedName;

public class WeatherAlert {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;

    public WeatherAlert(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
