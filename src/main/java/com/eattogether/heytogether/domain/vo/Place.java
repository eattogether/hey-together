package com.eattogether.heytogether.domain.vo;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Place {

    private double longitude;

    private double latitude;

    public Place() {
    }

    public Place(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Place)) {
            return false;
        }
        final Place place = (Place) o;
        return Double.compare(place.getLongitude(), getLongitude()) == 0 &&
                Double.compare(place.getLatitude(), getLatitude()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLongitude(), getLatitude());
    }

    @Override
    public String toString() {
        return "Place{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}