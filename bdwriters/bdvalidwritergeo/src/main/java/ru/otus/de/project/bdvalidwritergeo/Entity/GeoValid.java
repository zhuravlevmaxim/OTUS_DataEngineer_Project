package ru.otus.de.project.bdvalidwritergeo.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "geo", schema = "valid_data")
public class GeoValid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private Long number;
    @Column(name = "city")
    private String city;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "lng")
    private Double lng;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeoValid)) return false;
        GeoValid that = (GeoValid) o;
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
        return "GeoValid{" +
                "number='" + number + '\'' +
                ", city='" + city + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
