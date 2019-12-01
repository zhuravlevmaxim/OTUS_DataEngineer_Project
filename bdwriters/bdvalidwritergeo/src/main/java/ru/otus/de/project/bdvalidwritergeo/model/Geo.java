package ru.otus.de.project.bdvalidwritergeo.model;

import java.util.Objects;

public class Geo {

    private String number;

    private String city;

    private String datetime;

    private String lat;

    private String lng;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Geo)) return false;
        Geo geo = (Geo) o;
        return Objects.equals(number, geo.number) &&
                Objects.equals(city, geo.city) &&
                Objects.equals(datetime, geo.datetime) &&
                Objects.equals(lat, geo.lat) &&
                Objects.equals(lng, geo.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, city, datetime, lat, lng);
    }

    @Override
    public String toString() {
        return "Geo{" +
                "number='" + number + '\'' +
                ", city='" + city + '\'' +
                ", datetime='" + datetime + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
