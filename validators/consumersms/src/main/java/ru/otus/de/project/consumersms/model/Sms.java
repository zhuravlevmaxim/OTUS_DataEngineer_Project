package ru.otus.de.project.consumersms.model;

import java.util.Objects;

public class Sms {

    private String number;

    private String city;

    private String datetime;

    private String sms;

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

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sms)) return false;
        Sms sms1 = (Sms) o;
        return Objects.equals(number, sms1.number) &&
                Objects.equals(city, sms1.city) &&
                Objects.equals(datetime, sms1.datetime) &&
                Objects.equals(sms, sms1.sms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, city, datetime, sms);
    }

    @Override
    public String toString() {
        return "Sms{" +
                "number='" + number + '\'' +
                ", city='" + city + '\'' +
                ", datetime='" + datetime + '\'' +
                ", sms='" + sms + '\'' +
                '}';
    }
}
