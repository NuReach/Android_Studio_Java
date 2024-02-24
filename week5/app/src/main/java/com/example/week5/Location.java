package com.example.week5;

import androidx.annotation.NonNull;

public class Location {
    private double latitude;

    private double longitude;

    public Location(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    public String toString()
    {
        return "** " + latitude + ", " + longitude + " **";
    }
}
