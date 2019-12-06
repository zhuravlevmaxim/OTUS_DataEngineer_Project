package ru.otus.de.project.bdvalidwritersms.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "sms", schema = "valid_data")
public class SmsValid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private Long number;
    @Column(name = "city")
    private String city;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "sms")
    private String sms;

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

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SmsValid)) return false;
        SmsValid smsValid = (SmsValid) o;
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
        return "SmsValid{" +
                "id=" + id +
                ", number=" + number +
                ", city='" + city + '\'' +
                ", dateTime=" + dateTime +
                ", sms='" + sms + '\'' +
                '}';
    }
}
