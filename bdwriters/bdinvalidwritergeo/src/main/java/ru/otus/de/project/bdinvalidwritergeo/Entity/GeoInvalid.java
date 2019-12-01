package ru.otus.de.project.bdinvalidwritergeo.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "geo", schema = "invalid_data")
public class GeoInvalid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "city")
    private String city;
    @Column(name = "date_time")
    private String dateTime;
    @Column(name = "lat")
    private String lat;
    @Column(name = "lng")
    private String lng;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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
        if (!(o instanceof GeoInvalid)) return false;
        GeoInvalid that = (GeoInvalid) o;
        return number.equals(that.number) &&
                city.equals(that.city) &&
                dateTime.equals(that.dateTime) &&
                lat.equals(that.lat) &&
                lng.equals(that.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, city, dateTime, lat, lng);
    }

    @Override
    public String toString() {
        return "GeoInvalid{" +
                "number='" + number + '\'' +
                ", city='" + city + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}