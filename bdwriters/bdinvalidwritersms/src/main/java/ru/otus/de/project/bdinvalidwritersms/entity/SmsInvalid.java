package ru.otus.de.project.bdinvalidwritersms.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sms", schema = "valid_data")
public class SmsInvalid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "city")
    private String city;
    @Column(name = "date_time")
    private String dateTime;
    @Column(name = "sms")
    private String sms;

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

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SmsInvalid)) return false;
        SmsInvalid smsValid = (SmsInvalid) o;
        return Objects.equals(id, smsValid.id) &&
                Objects.equals(number, smsValid.number) &&
                Objects.equals(city, smsValid.city) &&
                Objects.equals(dateTime, smsValid.dateTime) &&
                Objects.equals(sms, smsValid.sms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, city, dateTime, sms);
    }

    @Override
    public String toString() {
        return "SmsInvalid{" +
                "id=" + id +
                ", number=" + number +
                ", city='" + city + '\'' +
                ", dateTime=" + dateTime +
                ", sms='" + sms + '\'' +
                '}';
    }
}
